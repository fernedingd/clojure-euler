; Task: Find the sum of all primes below 2.000.000
(ns problems.ten)

(defn dividable?
  [num divisor]
  (= 0 (mod num divisor)))

(defn square
  [num]
  (* num num))

(defn sieve
  "Returns all primes in the given range"
  [numbers]
  (loop [numbers numbers
         primes  []]
    (let [prime (first numbers)]
      (if (nil? prime)
        primes
        (let [remaining (filter #(not (dividable? % prime)) (rest numbers))]
          (recur remaining (conj primes prime)))))))

(defn filter-primes
  "Returns all primes from a list of numbers by comparing them with a list of known primes.
   The highest number can't be significantly higher than the highest prime squared."
  [numbers primes]
  (loop [numbers numbers
         primes primes]
    (let [prime (first primes)]
      (if (nil? prime)
        numbers
        (let [remaining (filter #(not (dividable? % prime)) numbers)]
          (recur remaining (rest primes)))))))

(defn sum-primes
  [limit]
  (let [split        (int (Math/sqrt limit))
        small-primes (sieve (range 2 (inc split)))
        high-primes  (filter-primes (range split limit) small-primes)]
    (+ (reduce + small-primes) (reduce + high-primes))))

(comment
  (->> (sieve (range 2 2000000))
       (reduce +)) ; waaaayyyy too slow
  
  (let [small-primes (sieve (range 2 10))]
    (concat small-primes 
            (filter-primes 
             (range (inc (last small-primes)) 
                    (square (last small-primes))) 
             small-primes)))
  
  (sieve (range 2 50))

  
  (sum-primes 2000000)

  :rcf)