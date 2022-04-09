# raylib-clj

> A Clojure Game Engine wrapping the fantastic Raylib 4.0 library with the
> native-performance Project Panama Clojure wrapper Coffi

## Structure

The main structure of this library follows that of raylib, but organized into
namespaced-functional blocks following the
(cheatsheet)[https://www.raylib.com/cheatsheet/cheatsheet.html].
Function names follow the raylib names, but formatted to the clojure-friendly kebab-case
style. Functions that mutate global state will end with a bang (`!`) and those
that return bools end in `?`. For example, the function to initialize the window
and OpenGL context is in the `core` module in the `window` namespace, so it
would be `raylib.core.window/init-window!`

## Example

```clojure
(ns basic-window
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
```

To run any of the included examples, use

```shell
clj -M:dev -m examples.x.y
```

such as

```shell
clj -M:dev -m examples.core.basic-window
```

for the above example.