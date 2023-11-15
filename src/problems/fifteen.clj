; Task: Calculate the amount of SE lettice paths in a 20x20 grid
(ns problems.fifteen)

(defn factorial
  [n]
  (reduce *' (range 1 (inc n))))

(defn binomial-coefficient
  [n k]
  (/ (factorial (+ n k)) (*' (factorial n) (factorial k))))

(defn lettice-paths
  [start end]
  (binomial-coefficient (- (end :x) (start :x)) (- (end :y) (start :y))))

(comment
  (factorial 3)

  (lettice-paths {:x 0 :y 0} {:x 20 :y 20})
  :rcf)