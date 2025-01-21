(ns core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.request :refer [body-string]]
            [babashka.http-client :as http]))

(defn handler [request]
  (let [{:keys [headers request-method]} request
        body (body-string request)
        requested-app (get headers "x-requested-app")
        requested-uri (get headers "x-requested-app-uri")]
    (println headers)
    (println "With method: " request-method)
    (if (and (some? body) (= :post request-method))
      (println "With body: " body)
      (println "With empty body"))
    (if (= request-method :post)
      (http/post (str "http://" requested-app requested-uri) {:headers {:content-type "application/json"}
                                                              :body body})
      (http/get (str "http://" requested-app requested-uri) {:headers {:content-type "application/json"}}))))

(defn -main [& args]
  (jetty/run-jetty
    (fn [request]
      (handler request))
    {:port 8080 :join? false}))

(-main 123)