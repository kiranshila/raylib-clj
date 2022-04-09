(ns raylib.core.drawing
  (:require
   [raylib.core]
   [raylib.structs :as rs]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn clear-background!
  "Set background color (framebuffer clear color)"
  {:arglists '([color])}
  "ClearBackground"
  [::rs/color] ::mem/void)

(defcfn begin-drawing!
  "Setup canvas (framebuffer) to start drawing"
  "BeginDrawing"
  [] ::mem/void)

(defcfn end-drawing!
  "End canvas drawing and swap buffers (double buffering)"
  "EndDrawing"
  [] ::mem/void)
