(ns graphicseditor.draw)

(def deltas [[-1 0] [1 0] [0 -1] [0 1]])


(defn neighbours [point]
  (map #(map + point %) deltas))
