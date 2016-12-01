(def input "L5, R1, L5, L1, R5, R1, R1, L4, L1, L3, R2, R4, L4, L1, L1, R2, R4, R3, L1, R4, L4, L5, L4, R4, L5, R1, R5, L2, R1, R3, L2, L4, L4, R1, L192, R5, R1, R4, L5, L4, R5, L1, L1, R48, R5, R5, L2, R4, R4, R1, R3, L1, L4, L5, R1, L4, L2, L5, R5, L2, R74, R4, L1, R188, R5, L4, L2, R5, R2, L4, R4, R3, R3, R2, R1, L3, L2, L5, L5, L2, L1, R1, R5, R4, L3, R5, L1, L3, R4, L1, L3, L2, R1, R3, R2, R5, L3, L1, L1, R5, L4, L5, R5, R2, L5, R2, L1, L5, L3, L5, L5, L1, R1, L4, L3, L1, R2, R5, L1, L3, R4, R5, L4, L1, R5, L1, R5, R5, R5, R2, R1, R2, L5, L5, L5, R4, L5, L4, L4, R5, L2, R1, R5, L1, L5, R4, L3, R4, L2, R3, R3, R3, L2, L2, L2, L1, L4, R3, L4, L2, R2, R5, L1, R2")

(defn createCmdListFromInput [inputStr]
  (.split ( .replaceAll inputStr " " "") ","))

(defn followCmd [cmdList direction index]
  (if (< index (alength cmdList)) 
    (let [turnDir (.substring (aget cmdList index) 0 1)
          distance (Integer/parseInt (.substring (aget cmdList index) 1))]
    (case direction
      "NORTH"
          (case turnDir
             "L"
               ( mapv - (followCmd cmdList "WEST" (+ index 1)) (vector distance 0))
             "R"
               ( mapv + (followCmd cmdList "EAST" (+ index 1)) (vector distance 0)))
      "EAST"
        (case turnDir
             "L"
               ( mapv + (followCmd cmdList "NORTH" (+ index 1)) (vector 0 distance))
             "R"
               ( mapv - (followCmd cmdList "SOUTH" (+ index 1)) (vector 0 distance)))
      "SOUTH"
        (case turnDir
             "L"
               ( mapv + (followCmd cmdList "EAST" (+ index 1)) (vector distance 0))
             "R"
               ( mapv - (followCmd cmdList "WEST" (+ index 1)) (vector distance 0)))
      "WEST"
        (case turnDir
             "L"
               ( mapv - (followCmd cmdList "SOUTH" (+ index 1)) (vector 0 distance))
             "R"
               ( mapv + (followCmd cmdList "NORTH" (+ index 1)) (vector 0 distance)))
       );; Case
       );; let   
    ;; Else return easter rabbit HQ as 0,0
    (vector 0 0)
    ) ;;if
    ) ;;defn


(println (reduce (fn [x y] (+ (Math/abs x) (Math/abs y))) (followCmd (createCmdListFromInput input) "NORTH" 0)))