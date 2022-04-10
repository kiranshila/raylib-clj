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
; TODO REMOVE
   [taoensso.tufte :as tufte :refer (defnp p profiled profile)]))

;; We'll request to send `profile` stats to `println`:
(tufte/add-basic-println-handler! {})

(def screen-width 800)
(def screen-height 450)

(def max-bunnies 50000)
(def max-batch-elements 8192.0)

(def bunny-texture (volatile! nil))
(def bunnies (volatile! []))

(defnp random-speed []
  {:x (/ (- (* (rand) 500) 250) 60)
   :y (/ (- (* (rand) 500) 250) 60)})

(defnp random-color []
  {:r (+ (rand-int 190) 50)
   :g (+ (rand-int 160) 80)
   :b (+ (rand-int 140) 100)
   :a 255})

(defnp generate-bunny []
  (when (< (count @bunnies) max-bunnies)
    (vswap! bunnies conj {:position (rcm/get-mouse-position)
                          :speed (random-speed)
                          :color (random-color)})))

(defnp update-bunny [bunny]
  (let [screen-width (rcw/get-screen-width)
        screen-height (rcw/get-screen-height)
        {:keys [width height]} @bunny-texture
        x-off (+ (get-in bunny [:position :x])
                 (/ width 2))
        y-off (+ (get-in bunny [:position :y])
                 (/ height 2))]
    (cond-> bunny
      true
      (update-in [:position :x] (partial + (get-in bunny [:speed :x])))

      true
      (update-in [:position :y] (partial + (get-in bunny [:speed :y])))

      (or (> x-off screen-width)
          (< x-off 0))
      (update-in [:speed :x] (partial * -1))

      (or (> y-off screen-height)
          (< y-off 0))
      (update-in [:speed :y] (partial * -1)))))

(defnp draw-bunny [bunny]
  (rtd/draw-texture!
   @bunny-texture
   (get-in bunny [:position :x])
   (get-in bunny [:position :y])
   (:color bunny)))

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [textures] example - bunnymark")
  ; Load the bunny texture
  (vreset! bunny-texture (rttl/load-texture! "examples/examples/textures/resources/wabbit_alpha.png"))
  ; Set target FPS
  (rct/set-target-fps! 60)
  ; Main Loop
  (while (not (rcw/window-should-close?))
    (when (rcm/is-mouse-button-down? (mouse-button :left))
      (doseq [_ (range 100)]
        (generate-bunny)))
    (vswap! bunnies (partial map update-bunny))
    (rcd/begin-drawing!)
    (rcd/clear-background! colors/raywhite)
    (doseq [bunny @bunnies]
      (draw-bunny bunny))
    (rsb/draw-rectangle! 0 0 screen-width 40 colors/black)
    (draw-text! (str "bunnies: " (count @bunnies)) 120 10 20 colors/green)
    (draw-text! (str "batched draw calls: " (+ 1 (int (/ (count @bunnies) max-batch-elements)))) 320 10 20 colors/maroon)
    (draw-fps! 10 10)
    (rcd/end-drawing!))
  ; Teardown
  (rcw/close-window!))
