(ns graphicseditor.core
  (:gen-class)
  (:use [clojure.string :only [join blank?]]
        graphicseditor.draw))

(defn createRow [length]
  (vec (repeat length :O)))

(defn createImage [columns rows]
  (vec (repeat rows (createRow columns))))

(defn clearImage [image]
  (createImage (count (first image)) (count image)))

(defn draw
  ([image x y colour]
     (assoc-in image [(- y 1) (- x 1)] colour))
  ([image point colour]
     (assoc-in image point colour)))

(defn drawVertical [image x y1 y2 colour]
  (if (<= y1 y2)
    (recur (draw image x y1 colour) x (inc y1) y2 colour)
    image))

(defn drawHorizontal [image y x1 x2 colour]
  (if (<= x1 x2)
    (recur (draw image x1 y colour) y (inc x1) x2 colour)
    image))

(defn displayRow [row]
  (join " " (map name row)))

(defn displayImage [image]
  (join "\n" (map displayRow image)))

(defn createCommand [args image]
  (let [[x y] args]
    (createImage (read-string x) (read-string y))))

(defn clearCommand [args image]
  (clearImage image))

(defn colourCommand [args image]
  (let [[x y colour] args]
    (draw image (read-string x) (read-string y) (keyword colour))))

(defn verticalCommand [args image]
  (let [[x y1 y2 colour] args]
    (drawVertical image (read-string x) (read-string y1) (read-string y2) (keyword colour))))

(defn horizontalCommand [args image]
  (let [[x1 x2 y colour] args]
    (drawHorizontal image (read-string y) (read-string x1) (read-string x2) (keyword colour))))

(defn showCommand [args image]
  (println (displayImage image))
  image)

(defn terminateCommand [args image] nil)

(defn fillCommand [args image]
  (let [[x y colour] args]
    (fill image (read-string x) (read-string y) (keyword colour))
    ))

(def commands {:I createCommand,
               :C clearCommand,
               :L colourCommand,
               :V verticalCommand,
               :H horizontalCommand,
               :S showCommand,
               :F fillCommand,
               :T terminateCommand})

(defn clean [args]
  (remove blank? (map str args)))

(defn processCommands [image]
  (if (not (nil? image))
    (let [[rawcommand & args] (read-line)
          command (commands (keyword (str rawcommand)))]
    (recur (command (clean args) image))
    )))

(defn -main [& args]
  (println "Ready... Please enter commands:")
  (processCommands []))
