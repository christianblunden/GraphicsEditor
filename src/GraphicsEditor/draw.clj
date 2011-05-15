(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])

(defn valid? [point size]
  (every? #(<= 0 %) point))

(defn neighbours [point size]
  (filter (fn [new-point] (valid? new-point size))
          (map #(map + point %) deltas)))
