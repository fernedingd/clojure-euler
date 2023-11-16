;; Task: Fid the largest palindrome number that is the product of two three-digit numbers
(ns problems.0.4)

(defn palindrome?
  [value]
  (= (seq (str value)) (reverse (str value))))

(defn cartesian-product
  [range1 range2]
  (for [value1 range1
        value2 range2]
    (* value1 value2)))

(comment
  (->> (cartesian-product (range 100 1000) (range 100 1000))
       (distinct)
       (filter palindrome?)
       (sort) 
       (last))
  :rcf)