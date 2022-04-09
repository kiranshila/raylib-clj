(ns raylib.shapes.basic
  (:require
   [raylib.core]
   [raylib.structs :as rs]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

; ...

(defcfn draw-circle-v!
  "Draw a color-filled circle (Vector version)"
  {:arglists '([center radius color])}
  "DrawCircleV"
  [::rs/vector-2 ::mem/float ::rs/color] ::mem/void)

; ...
