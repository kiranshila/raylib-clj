(ns raylib.textures.drawing
  (:require
   [raylib.core]
   [raylib.structs :as rs]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn draw-texture!
  "Draw a texture"
  {:arglists '([texture x y tint])}
  "DrawTexture"
  [::rs/texture ::mem/int ::mem/int ::rs/color] ::mem/void)

; ...
