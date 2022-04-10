(ns raylib.textures.texture-loading
  (:require
   [raylib.core]
   [raylib.structs :as rs]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn load-texture!
  "Load texture from file into GPU memory (VRAM)"
  {:arglists '([filename])}
  "LoadTexture"
  [::mem/c-string] ::rs/texture)

; ...
