; Task: Find the difference between the sum of squares and the square of the sum of 1..100
(ns problems.six)

(defn square 
  [num]
  (* num num))

(comment
  (def lower 1)
  (def upper 101)

  (abs (- (->> (range lower upper)
               (map square)
               (reduce +))

          (square (->> (range lower upper)
                       (reduce +)))))
  
  :rcf)