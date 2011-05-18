(ns graphicseditor.test-core
  (:use graphicseditor.core
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
         (draw image 2 2 :C) => (just [[:O :O] [:O :C]])
         (draw image [0 0] :R) => (just [[:R :O] [:O :O]])))

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
         (displayRow (first image)) =>  (just "A B C")
         (displayImage image) =>  (just "A B C\nD E F")
                  ))

(facts "about create command"
       (commands :I) => (exactly createCommand)
       (createCommand ["3" "2"] []) => (just [[:O :O :O] [:O :O :O]])
       )

(facts "about out of bounds create command"
       (createCommand ["0" "2"] []) => nil?
       (provided
        (println "Image size must be between 1 and 250") => nil))

(facts "about clearing command"
       (commands :C) => (exactly clearCommand)
       (clearCommand [] [[:A :B] [:C :D]]) => (just [[:O :O] [:O :O]]))

(facts "about colour command"
       (commands :L) => (exactly colourCommand)
       (colourCommand ["1" "1" "R"] [[:O :O] [:O :O]]) => (just [[:R :O] [:O :O]])
       )

(facts "about vertical command"
       (commands :V) => (exactly verticalCommand)
       (verticalCommand ["2" "1" "2" "G"] [[:O :O] [:O :O]]) => (just [[:O :G] [:O :G]]))

(facts "about horizontal command"
       (commands :H) => (exactly horizontalCommand)
       (horizontalCommand ["1" "2" "2" "Y"] [[:O :O] [:O :O]]) => (just [[:O :O] [:Y :Y]]))


(facts "about show command"
       (commands :S) => (exactly showCommand)
       (showCommand [] [[:A :B] [:C :D]]) => [[:A :B] [:C :D]])

(facts "about terminate command"
       (commands :T) => (exactly terminateCommand)
       (terminateCommand [] []) => nil?)

(facts "about fill command"
       (commands :F) => (exactly fillCommand)
       (fillCommand ["1" "2" "R"] [[:O :O] [:O :O]]) => (just [[:R :R] [:R :R]]))

(fact "about getting input"
      (get-input) => (just ["V" "10" "250" "2" "C"])
      (provided
       (read-line) => "V 10 250 2 C"))
