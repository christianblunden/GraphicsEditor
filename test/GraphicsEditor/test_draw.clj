(ns graphicseditor.test-draw
  (:use graphicseditor.draw
        clojure.test
        midje.sweet))

(facts "about neighbours"
       (let [image [[:O :O :O] [:O :O :O] [:O :O :O]]]
         (neighbours [1 1] [3 3]) => (just [0 1] [2 1] [1 0] [1 2])
         (valid? [0 0] [2 3]) => true
         (valid? [-1 0] [2 3]) => false
         (valid? [0 -1] [2 3]) => false
         (valid? [3 3] [2 3]) => false
         (valid? [2 4] [2 3]) => false
         (valid? [1 2] [2 3]) => true
         (valid? [0 0] [1 1]) => true
         (neighbours [0 0] [3 3]) => (just [1 0] [0 1])
         (neighbours [3 2] [4 3]) => (just [2 2] [3 1])
         (neighbours [0 0] [1 1]) => empty?
         ))

(facts "about coordinate conversion"
       (coord-of 1 1) => (just [0 0])
       (coord-of 2 5) => (just [4 1]))

(facts "about colour picker"
       (let [image [[:O :O] [:O :A]]]
         (colour-at? [0 0] image) => :O
         (colour-at? [1 1] image) => :A
         ))

(facts "about image sizer"
       (size-of [[:O :O] [:O :O]]) => (just [2 2])
       (size-of [[:A :B :C] [:D :E :F]]) => (just [3 2])
       )

(facts "about fill"
       (let [image [[:O :O] [:O :A]]]
         (fill image 2 2 :C) => (just [[:O :O] [:O :C]])
         )
       )
