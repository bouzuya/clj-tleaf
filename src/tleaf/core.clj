(ns tleaf.core
  (:gen-class)
  (:import (java.util Date)
           (org.thymeleaf TemplateEngine)
           (org.thymeleaf.context Context)
           (org.thymeleaf.resourceresolver FileResourceResolver)
           (org.thymeleaf.templateresolver TemplateResolver)))

(defn create-engine
  []
  (let [tr (TemplateResolver.)]
    (.setResourceResolver tr (FileResourceResolver.))
    (.setTemplateMode tr "XHTML")
    (.setPrefix tr "src/tleaf/")
    (.setSuffix tr ".html")
    (let [engine (TemplateEngine.)]
      (.setTemplateResolver engine tr)
      engine)))

(defn create-context
  [m]
  (reduce (fn [c [k v]] (.setVariable c k v) c) (Context.) m))

(defn -main
  [& args]
  (let [engine (create-engine)
        context (create-context {"variable" "foobarbaz"
                                 "datetime" (.toString (Date.))
                                 "messages" [{"name" "bouzuya" "text" "hello!"}
                                             {"name" "emanon001" "text" "hello!!"}]})]
    (print (.process engine "index" context))))


