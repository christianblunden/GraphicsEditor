(ns graphicseditor.test-draw
  (:use graphicseditor.draw
        clojure.test
        midje.sweet))

(facts "about neighbours"
       (let [image [[:O :O :O] [:O :O :O] [:O :O :O]]]
         (neighbours [1 1]) => (just [0 1] [2 1] [1 0] [1 2])

         ))
