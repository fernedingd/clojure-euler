; Task: Find the 10001th prime number
(ns problems.seven)

(defn dividable?
  [num divisor]
  (= 0 (mod num divisor)))

(defn sieve
  [numbers primes]
  (let [pre-filtered-numbers (filter #(not-any? (partial dividable? %) primes) numbers)]
   (loop [numbers pre-filtered-numbers
         primes  primes]
    (let [prime (first numbers)]
      (if (nil? prime)
        primes
        (let [remaining (filter #(not (dividable? % prime)) (rest numbers))]
          (recur remaining (conj primes prime))))))))

(defn get-primes
  [min]
  (loop [limit  min
         primes []]
    (let [current-count (count primes)]
      (if (>= current-count min)
        primes 
          (let [next-primes (sieve (range (+ 1 (or (last primes) 1)) limit) primes)]
                           (recur (+ limit limit) next-primes))))))

(comment
  (sieve (range 2 10) [])
  (sieve (range (+ 1 7) 20) [2 3 5 7])
  (count (get-primes 1000)) 
  
  (nth (get-primes 10001) 10000)
  :rcf)