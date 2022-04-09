(ns raylib.core.window
  (:require
   [raylib.core]
   [raylib.internals :as ri]
   [coffi.mem :as mem]
   [coffi.ffi :refer [defcfn]]))

(defcfn init-window!
  "Initialize window and OpenGL context"
  {:arglists '([width height title])}
  "InitWindow"
  [::mem/int ::mem/int ::mem/c-string] ::mem/void)

(defcfn window-should-close?
  "Check if KEY_ESCAPE pressed or Close icon pressed"
  "WindowShouldClose"
  [] ::ri/bool)

(defcfn close-window!
  "Close window and unload OpenGL context"
  "CloseWindow" [] ::mem/void)

(defcfn is-window-ready?
  "Check if window has been initialized successfully"
  "IsWindowReady"
  [] ::ri/bool)

(defcfn is-window-fullscreen?
  "Check if window is currently fullscreen"
  "IsWindowFullscreen"
  [] ::ri/bool)

(defcfn is-window-hidden?
  "Check if window is currently hidden (only PLATFORM_DESKTOP)"
  "IsWindowHidden"
  [] ::ri/bool)

(defcfn is-window-minimized?
  "Check if window is currently minimized (only PLATFORM_DESKTOP)"
  "IsWindowMinimized"
  [] ::ri/bool)

(defcfn is-window-maximized?
  "Check if window is currently maximized (only PLATFORM_DESKTOP)"
  "IsWindowMaximized"
  [] ::ri/bool)

(defcfn is-window-focused?
  "Check if window is currently focused (only PLATFORM_DESKTOP)"
  "IsWindowFocused"
  [] ::ri/bool)

(defcfn is-window-resized?
  "Check if window has been resized last frame"
  "IsWindowResized"
  [] ::ri/bool)
