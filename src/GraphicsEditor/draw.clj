(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])

(defn valid? [point]
  (every? #(<= 0 %) point))

(defn neighbours [point]
  (filter valid?
          (map #(map + point %) deltas)))
