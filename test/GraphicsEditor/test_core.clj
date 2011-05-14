(ns GraphicsEditor.test-core
  (:use GraphicsEditor.core
	clojure.test
	midje.sweet))

(facts "about image row creation"
       (createRow 0) => vector?
       (createRow 5) => (just [:O :O :O :O :O])
       )

(facts "about image creation"
       (createImage 2 2) => vector?
       (createImage 2 3) => (just [[:O :O] [:O :O] [:O :O]])
       )

(facts "about clearing an image"
       (let [currentimage [[:R :G] [:B :Y]]]
         (clearImage currentimage) => vector?
         (clearImage currentimage) => (just [[:O :O] [:O :O]])
         ))

(facts "about drawing a pixel on image"
       (let [image [[:O :O] [:O :O]]]
         (drawPixel image 2 2 :C) => (just [[:O :O] [:O :C]])
         (drawPixel image 1 2 :R) => (just [[:O :O] [:R :O]])
         (drawPixel image 2 1 :Z) => (just [[:O :Z] [:O :O]])
         ))

(facts "about drawing vertical line on image"
       (let [image [[:O :O] [:O :O] [:O :O] [:O :O]]]
         (drawVertical image 2 2 3 :R) => (just [[:O :O] [:O :R] [:O :R] [:O :O]])
         (drawVertical image 1 1 4 :P) => (just [[:P :O] [:P :O] [:P :O] [:P :O]])
         ))

(facts "about drawing a vertical line on image"
       (let [image [[:O :O :O :O] [:O :O :O :O]]]
         (drawHorizontal image 2 2 3 :A) => (just [[:O :O :O :O] [:O :A :A :O]])
         (drawHorizontal image 1 1 3 :B) => (just [[:B :B :B :O] [:O :O :O :O]])
         ))

(facts "about showing an image"
       (let [image [[:A :B :C] [:D :E :F]]]
         (displayRow (first image)) => "A B C"
         ))
