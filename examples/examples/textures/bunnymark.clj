(ns examples.textures.bunnymark
  (:require
   [raylib.enums :refer [mouse-button]]
   [raylib.core.timing :as rct]
   [raylib.core.window :as rcw]
   [raylib.core.mouse :as rcm]
   [raylib.textures.texture-loading :as rttl]
   [raylib.textures.drawing :as rtd]
   [raylib.text.drawing :refer [draw-text! draw-fps!]]
   [raylib.core.drawing :as rcd]
   [raylib.shapes.basic :as rsb]
   [raylib.colors :as colors]
   [coffi.mem :as mem]
   [raylib.structs :as rs]))

(def screen-width 1920)
(def screen-height 1080)

(def max-bunnies 500000)
(def max-batch-elements 8192.0)

(def bunny-texture (volatile! nil))
(def bunnies (volatile! []))

(defn random-speed []
  {:x (/ (- (* (rand) 500) 250) 60)
   :y (/ (- (* (rand) 500) 250) 60)})

(defn random-color []
  {:r (+ (rand-int 190) 50)
   :g (+ (rand-int 160) 80)
   :b (+ (rand-int 140) 100)
   :a 255})

(def ^:dynamic *bunny-scope*)

(defrecord Bunny [position speed color])

(defn generate-bunny []
  (when (< (count @bunnies) max-bunnies)
    (vswap! bunnies conj (Bunny. (rcm/get-mouse-position)
                                 (random-speed)
                                 (mem/serialize (random-color) ::rs/color *bunny-scope*)))))

(defn update-bunny [^Bunny bunny]
  (let [{:keys [x y]} (.-position bunny)
        {dx :x dy :y} (.-speed bunny)
        {:keys [width height]} @bunny-texture
        new-x (+ x dx)
        new-y (+ y dy)
        x-off (+ new-x (/ width 2.0))
        y-off (+ new-y (/ height 2.0))]
    (Bunny. {:x new-x :y new-y}
            {:x (if (or (> x-off screen-width) (< x-off 0))
                  (unchecked-negate dx)
                  dx)
             :y (if (or (> y-off screen-height) (< y-off 0))
                  (unchecked-negate dy)
                  dy)}
            (.-color bunny))))

(defn draw-bunny [bunny]
  (rtd/draw-texture!*
   (:mem @bunny-texture)
   (-> bunny :position :x)
   (-> bunny :position :y)
   (:color bunny)))

(defn draw-info! []
  (rsb/draw-rectangle! 0 0 screen-width 40 colors/black)
  (draw-text! (str "bunnies: " (count @bunnies)) 120 10 20 colors/green)
  (draw-text! (str "batched draw calls: " (+ 1 (int (/ (count @bunnies) max-batch-elements)))) 320 10 20 colors/maroon)
  (draw-fps! 10 10))

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [textures] example - bunnymark")
  ; Load the bunny texture
  (let [texture (rttl/load-texture! "examples/examples/textures/resources/wabbit_alpha.png")]
    (vreset! bunny-texture {:mem (mem/serialize texture ::rs/texture)
                            :width (:width texture)
                            :height (:height texture)}))
  ; Set target FPS
  (rct/set-target-fps! 60)
  ; Main Loop
  (binding [*bunny-scope* (mem/connected-scope)]
    (try
      (while (not (rcw/window-should-close?))
        (when (rcm/is-mouse-button-down? (mouse-button :left))
          (doseq [_ (range 100)]
            (generate-bunny)))
        (vswap! bunnies (partial mapv update-bunny))
        (rcd/begin-drawing!)
        (rcd/clear-background! colors/raywhite)
        (doseq [bunny @bunnies]
          (draw-bunny bunny))
        (draw-info!)
        (rcd/end-drawing!))
      (finally
        (vreset! bunnies []))))
  ; Teardown
  (rcw/close-window!))
