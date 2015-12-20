-- Table: "mealDish"

-- DROP TABLE "mealDish";

CREATE TABLE "mealDish"
(
  "mealID" numeric,
  "dishID" numeric
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "mealDish"
  OWNER TO admin;
