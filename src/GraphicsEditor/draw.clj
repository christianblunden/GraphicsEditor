(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])

(defn size-of [image]
  [(count (first image)) (count image)])

(defn coord-of [x y]
  [(- y 1) (- x 1)])

(defn valid? [point size]
  (let [[x y] point
        [max-x max-y] size]
    (and (< -1 x max-x) (< -1 y max-y))))

(defn colour-at? [point image]
  (get-in image point))

(defn neighbours [point size]
  (filter (fn [new-point] (valid? new-point size))
          (map #(map + point %) deltas)))

(defn invalid-node [node visited colour image]
  (or (contains? visited node)
      (not= colour (colour-at? node image))))

(defn fill [image x y new-colour]
  (let [origin (coord-of x y)
        old-colour (colour-at? origin image)
        size (size-of image)]
    (loop [queue (conj clojure.lang.PersistentQueue/EMPTY origin)
           visited {origin nil}
           new-image image]
      (when-let [node (peek queue)]
        (let [nbrs (remove #(invalid-node % visited old-colour image)
                           (neighbours node size))]
          (if (and (empty? nbrs) (empty? (pop queue)))
            (assoc-in new-image node new-colour)
            (recur (into (pop queue) nbrs)
                 (reduce #(assoc %1 %2 node) visited nbrs)
                 (assoc-in new-image node new-colour)))
          )))
))
