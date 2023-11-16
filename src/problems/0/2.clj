;; Task: Find the sum of all fibonacci sequence values up to 4,000,000
(ns problems.0.2)

(defn fibonacci [[a b]] [b (+ a b)])

(def fibonacci-sequence (map last (iterate fibonacci [1 1])))

(comment
  (->> (take-while #(-> % (< 4000000)) fibonacci-sequence)
       (filter even?)
       (reduce +))
  :rcf)