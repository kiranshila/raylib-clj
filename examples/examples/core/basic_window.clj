(ns examples.core.basic-window
  (:require
   [raylib.core.window :as rcw]
   [raylib.core.timing :as rct]
   [raylib.core.drawing :as rcd]
   [raylib.text.drawing :as rtd]
   [raylib.colors :as colors]))

(def screen-width 800)
(def screen-height 450)

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [core] example - basic window")
  (rct/set-target-fps! 60)
  ;; Main Game Loop
  (while (not (rcw/window-should-close?))
    (rcd/begin-drawing!)
    (rcd/clear-background! colors/raywhite)
    (rtd/draw-text! "Congrats! You created your first window!" 190 200 20 colors/lightgray)
    (rcd/end-drawing!))
  (rcw/close-window!))
