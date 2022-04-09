(ns raylib.core.timing
  (:require
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn set-target-fps!
  "Set target FPS (maximum)"
  {:arglists '([fps])}
  "SetTargetFPS"
  [::mem/int] ::mem/void)

(defcfn get-target-fps
  "Get current FPS"
  "GetFPS"
  [] ::mem/int)

(defcfn get-frame-time
  "Get time in seconds for last frame drawn (delta time)"
  "GetFrameTime"
  [] ::mem/float)

(defcfn get-time
  "Get elapsed time in seconds since `init-window!`"
  "GetTime"
  [] ::mem/double)
