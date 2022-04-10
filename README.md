# raylib-clj

> A Clojure Game Engine wrapping the fantastic [Raylib](https://www.raylib.com/) library with the
> Project Panama Clojure wrapper [Coffi](https://github.com/IGJoshua/coffi)

## Requirements

You must have at least Java 17 to use the new Project Panama and Raylib
installed as a system-level dynamic library. We can probably ship binaries eventually.

## Structure

The main structure of this library follows that of raylib, but organized into
namespaced-functional blocks following the
[cheatsheet](https://www.raylib.com/cheatsheet/cheatsheet.html).
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

## Benchmarks

To bench our wrapper, we will compare against the tried and true
[bunnymark](https://github.com/raysan5/raylib/blob/master/examples/textures/textures_bunnymark.c).
Most other wrappers target this as well, so it'll be nice to compare.
As expected, we are about 4x slower than the pure Java JNI approach and quite a
bit slower than pure-C. To be fair, you would use instancing for something like
this as we are putting FFI calls in the tight inner loop, which ends up being
our bottleneck here.

| Language      | Bunnies @ 60 FPS | Speed |
| ------------- | ---------------- | ----- |
| C (Reference) | 216200           | 1x    |
| Jaylib        | 64700            | 0.3x  |
| raylib-clj    | 16000            | 0.07x |
