(ns raylib.enums
  (:require
   [coffi.mem :as mem]))

(def keyboard-key
  {:null 0
   ; Alphanumeric
   :apostrophe 39
   :comma 44
   :minus 45
   :period 46
   :slash 47
   :zero 48
   :one 49
   :two 50
   :three 51
   :four 52
   :five 53
   :six 54
   :seven 55
   :eight 56
   :nine 57
   :semicolon 59
   :equal 61
   :a 65
   :b 66
   :c 67
   :d 68
   :e 69
   :f 70
   :g 71
   :h 72
   :i 73
   :j 74
   :k 75
   :l 76
   :m 77
   :n 78
   :o 79
   :p 80
   :q 81
   :r 82
   :s 83
   :t 84
   :u 85
   :v 86
   :w 87
   :x 88
   :y 89
   :z 90
   :left-bracket 91
   :backslash 92
   :right-bracket 93
   :grave 96
   ; Function Keys
   :space 32
   :escape 256
   :enter 257
   :tab 258
   :backspace 259
   :insert 260
   :delete 261
   :right 262
   :left 263
   :down 264
   :up 265
   :page-up 266
   :page-down 264
   :home 268
   :end 269
   :caps-lock 280
   :scroll-lock 281
   :num-lock 282
   :print-screen 283
   :pause 284
   :f1 290
   :f2 291
   :f3 292
   :f4 293
   :f5 294
   :f6 295
   :f7 296
   :f8 297
   :f9 298
   :f10 299
   :f11 300
   :f12 301
   :left-shift 340
   :left-control 341
   :left-alt 342
   :left-super 343
   :right-shift 344
   :right-control 345
   :right-alt 346
   :right-super 347
   :kb-menu 348
   ; Keypad
   :kp-0 320
   :kp-1 321
   :kp-2 322
   :kp-3 323
   :kp-4 324
   :kp-5 325
   :kp-6 326
   :kp-7 327
   :kp-8 328
   :kp-9 329
   :kp-decimal 330
   :kp-divide 331
   :kp-multiply 332
   :kp-subtract 333
   :kp-add 334
   :kp-enter 335
   :kp-equal 336})

(def mouse-button
  {:left 0
   :right 1
   :middle 2
   :side 3
   :extra 4
   :forward 5
   :back 6})
