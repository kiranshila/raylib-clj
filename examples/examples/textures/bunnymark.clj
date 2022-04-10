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
   [raylib.colors :as colors]))

(def screen-width 800)
(def screen-height 450)

(def max-bunnies 50000)
(def max-batch-elements 8192)

(def bunny-texture (atom nil))
(def bunnies (atom []))

(defn random-speed []
  {:speed {:x (/ (- (* (rand) 500) 250) 60)
           :y (/ (- (* (rand) 500) 250) 60)}})

(defn random-color []
  {:r (+ (rand-int 190) 50)
   :g (+ (rand-int 160) 80)
   :b (+ (rand-int 140) 100)
   :a 255})

(defn generate-bunny []
  (when (< (count @bunnies) max-bunnies)
    (swap! bunnies conj {:position (rcm/get-mouse-position)
                         :speed (random-speed)
                         :color (random-color)})))

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [textures] example - bunnymark")
  ; Load the bunny texture
  (reset! bunny-texture (rttl/load-texture! "examples/examples/textures/resources/wabbit_alpha.png"))
  ; Set target FPS
  (rct/set-target-fps! 60)
  ; Main Loop
  (while (not (rcw/window-should-close?))
    (when (rcm/is-mouse-button-down? (mouse-button :left))
      (doseq [_ (range 100)]
        (generate-bunny)))
    (rcd/begin-drawing!)
    (rcd/clear-background! colors/raywhite)
    (doseq [bunny @bunnies]
      (rtd/draw-texture!
       @bunny-texture
       (get-in bunny [:position :x])
       (get-in bunny [:position :y])
       (:color bunny)))
    (rsb/draw-rectangle! 0 0 screen-width 40 colors/black)
    (draw-text! (str "bunnies: " (count @bunnies)) 120 10 20 colors/green)
    (draw-text! (str "batched draw calls: " (+ 1 (/ (count @bunnies) max-batch-elements))) 320 10 20 colors/maroon)
    (draw-fps! 10 10)
    (rcd/end-drawing!))
  ; Teardown
  (rcw/close-window!))
