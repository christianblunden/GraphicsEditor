(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])

(defn valid? [point size]
  (and (<= 0 (first point) (first size))
       (<= 0 (last point) (last size))))

(defn neighbours [point size]
  (filter (fn [new-point] (valid? new-point size))
          (map #(map + point %) deltas)))
