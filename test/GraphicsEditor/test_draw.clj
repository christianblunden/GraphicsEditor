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

(facts "about filler")
