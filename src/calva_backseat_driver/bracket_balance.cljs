(ns calva-backseat-driver.bracket-balance
  (:require ["parinfer" :as parinfer]))

(defn infer-parens
  "Infer parens from the indentation"
  [{:ex/keys [dispatch!]
    :calva/keys [text]}]
  (dispatch! [[:app/ax.log :debug "[Server] Infering brackets for:" text]])
  (try
    (let [result (parinfer/indentMode  text #js {:partialResult true})]
      (-> result
          js->clj
          (dissoc "parenTrails")
          clj->js))
    (catch :default e
      #js {:error (.-message e)})))

(comment
  (infer-parens {:ex/dispatch! println
                 :calva/text "(def foo [a b"}))