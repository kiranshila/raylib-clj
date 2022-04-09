(ns raylib.core
  (:require
   [coffi.ffi :as ffi]))

;; Load the raylib library
(ffi/load-system-library "raylib")
