; Task: Find the smallest number that is evenly divisible by all numbers from 1 to 20
(ns problems.five)

(defn multiple?
  [number dividor]
  (zero? (mod number dividor)))

(defn find-smallest-multiple
  [range]
  (loop [value 1] 
    (if (every? #(multiple? value %) range) value
        (recur (inc value)))))

(comment 
  (find-smallest-multiple (range 1 21)) 
  :rcf)