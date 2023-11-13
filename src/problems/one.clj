;; Task: Find the sum of all multiples of 3 or 5 below 1000
(ns problems.one)

(defn multiple? [base num] (= 0 (mod num base)))

(defn multiple-three-five? [num] (or (multiple? 3 num) (multiple? 5 num)))

(comment
  (->> (range 1 1000)
       (filter multiple-three-five?)
       (reduce +))
  :rcf)