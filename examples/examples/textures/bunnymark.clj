(ns examples.textures.bunnymark
  (:require
   [raylib.core.timing :as rct]
   [raylib.core.window :as rcw]
   [raylib.textures.texture-loading :as rttl]))

(def screen-width 800)
(def screen-height 450)

(def max-bunnies 50000)
(def max-batch-elemeents 8192)

(defn -main []
  (rcw/init-window! screen-width screen-height "raylib [textures] example - bunnymark")
  ; Load the bunny texture

  (rct/set-target-fps! 60))
