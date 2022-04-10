(ns examples.core.input-keys
  (:require
   [raylib.core.window :as rcw]
   [raylib.core.timing :as rct]
   [raylib.core.drawing :as rcd]
   [raylib.core.keyboard :as rck]
   [raylib.text.drawing :as rtd]
   [raylib.shapes.basic :as rsb]
   [raylib.enums :refer [keyboard-key]]
   [raylib.colors :as colors]))

(def screen-width 800)
(def screen-height 450)

(def ball-position (atom {:x (/ screen-width 2)
                          :y (/ screen-height 2)}))

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [core] example - keyboard input")
  (rct/set-target-fps! 60)
  ;; Main Game Loop
  (while (not (rcw/window-should-close?))
    ; Updates
    (when (rck/is-key-down? (keyboard-key :right))
      (swap! ball-position update :x (partial + 2)))
    (when (rck/is-key-down? (keyboard-key :left))
      (swap! ball-position update :x (partial + -2)))
    (when (rck/is-key-down? (keyboard-key :up))
      (swap! ball-position update :y (partial + -2)))
    (when (rck/is-key-down? (keyboard-key :down))
      (swap! ball-position update :y (partial + 2)))
    ; Draw
    (rcd/begin-drawing!)
    (rcd/clear-background! colors/raywhite)
    (rtd/draw-text! "move the ball with arrow keys" 10 10 20 colors/darkgray)
    (rsb/draw-circle-v! @ball-position 50 colors/maroon)
    (rcd/end-drawing!))
  (rcw/close-window!))
