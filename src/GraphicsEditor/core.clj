(ns GraphicsEditor.core
  (:use clojure.string))

(defn createRow [length]
  (vec (repeat length :O)))

(defn createImage [columns rows]
  (vec (repeat rows (createRow columns))))

(defn clearImage [image]
  (createImage (count (first image)) (count image)))

(defn drawPixel [image x y colour]
  (assoc-in image [(- y 1) (- x 1)] colour))

(defn drawVertical [image x y1 y2 colour]
  (if (<= y1 y2)
    (recur (drawPixel image x y1 colour) x (inc y1) y2 colour)
    image))

(defn drawHorizontal [image y x1 x2 colour]
  (if (<= x1 x2)
    (recur (drawPixel image x1 y colour) y (inc x1) x2 colour)
    image))

(defn displayRow [row]
  (join " " (map name row)))

(defn displayImage [image]
  (map displayRow image))

(defn createCommand [args image]
  (let [[x y] args]
    (createImage (read-string x) (read-string y))))

(defn clearCommand [args image]
  (clearImage image))

(defn colourCommand [args image]
  (let [[x y colour] args]
    (drawPixel image (read-string x) (read-string y) (keyword colour))))

(def commands {:I createCommand,
               :C clearCommand,
               :L colourCommand})


