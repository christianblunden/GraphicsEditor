(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])

(defn valid? [point size]
  (let [[x y] point
        [max-x max-y] size]
    (and (< -1 x max-x) (< -1 y max-y))
    ))

(defn neighbours [point size]
  (filter (fn [new-point] (valid? new-point size))
          (map #(map + point %) deltas)))
