; Task: Find the longest collatz sequence starting under 1,000,000
(ns problems.fourteen)

(defn collatz-next
  "Returns the next number of the collatz sequence.
   Returns n/2 for even numbers
   Returns 3n+1 for odd numbers"
  [n]
  (if (even? n)
    (/ n 2)
    (+ 1 (* 3 n))))

(def sequence-lengths (atom {1 1}))

(defn collatz-sequence-length
  [number]
  (or (@sequence-lengths number)
      (inc (collatz-sequence-length (collatz-next number)))))

(defn longest-collatz-sequence
  [limit]
  (loop [number 1
         longest {:number 0 :length -1}]
    (if (>= number limit)
      longest
      (let [current (collatz-sequence-length number)]
        (swap! sequence-lengths assoc number current)
        (if (> current (longest :length))
          (recur (inc number) {:number number :length current})
          (recur (inc number) longest))))))

(comment
  (collatz-sequence-length 13)

  (->> (range 1 1000000)
       (map #(vector [(collatz-sequence-length %) %]))
       (sort)
       (last)) ; wayyyy too slow
  
  (longest-collatz-sequence 1000000)
  :rcf)