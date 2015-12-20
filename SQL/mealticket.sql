--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: dish; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE dish (
    "dishID" numeric,
    "dishName" text,
    "foodType" text
);


ALTER TABLE dish OWNER TO admin;

--
-- Name: ingredients; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE ingredients (
    ingredientid numeric NOT NULL,
    ingredientname text
);


ALTER TABLE ingredients OWNER TO admin;

--
-- Name: meal; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE meal (
    "mealID" numeric,
    "userID" numeric,
    "mealDay" text,
    "mealTimes" text,
    "mealName" text
);


ALTER TABLE meal OWNER TO admin;

--
-- Name: mealDish; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE "mealDish" (
    "mealID" numeric,
    "dishID" numeric
);


ALTER TABLE "mealDish" OWNER TO admin;

--
-- Name: recipe; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE recipe (
    recipeid numeric(12,0) NOT NULL,
    recipename text,
    recipetype text
);


ALTER TABLE recipe OWNER TO admin;

--
-- Name: recipeingredients; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE recipeingredients (
    recipeid numeric(12,0) NOT NULL,
    ingredientid numeric(12,0) NOT NULL,
    recingcount numeric(6,2),
    recingmeasuretype text
);


ALTER TABLE recipeingredients OWNER TO admin;

--
-- Name: user; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE "user" (
    "userID" numeric,
    "userName" text,
    "userEmail" text,
    "userPassword" text
);


ALTER TABLE "user" OWNER TO admin;

--
-- Name: weekPlan; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE "weekPlan" (
    "weekPlanID" numeric,
    "daysInPlan" text,
    "mealTimes" text
);


ALTER TABLE "weekPlan" OWNER TO admin;

--
-- Name: weekPlanMeal; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE "weekPlanMeal" (
    "weekPlanID" numeric,
    "mealID" numeric
);


ALTER TABLE "weekPlanMeal" OWNER TO admin;

--
-- Data for Name: dish; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY dish ("dishID", "dishName", "foodType") FROM stdin;
\.


--
-- Data for Name: ingredients; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY ingredients (ingredientid, ingredientname) FROM stdin;
\.


--
-- Data for Name: meal; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY meal ("mealID", "userID", "mealDay", "mealTimes", "mealName") FROM stdin;
\.


--
-- Data for Name: mealDish; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY "mealDish" ("mealID", "dishID") FROM stdin;
\.


--
-- Data for Name: recipe; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY recipe (recipeid, recipename, recipetype) FROM stdin;
\.


--
-- Data for Name: recipeingredients; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY recipeingredients (recipeid, ingredientid, recingcount, recingmeasuretype) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY "user" ("userID", "userName", "userEmail", "userPassword") FROM stdin;
\.


--
-- Data for Name: weekPlan; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY "weekPlan" ("weekPlanID", "daysInPlan", "mealTimes") FROM stdin;
\.


--
-- Data for Name: weekPlanMeal; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY "weekPlanMeal" ("weekPlanID", "mealID") FROM stdin;
\.


--
-- Name: pk_ingredientid; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY ingredients
    ADD CONSTRAINT pk_ingredientid PRIMARY KEY (ingredientid);


--
-- Name: pk_recipeid; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY recipe
    ADD CONSTRAINT pk_recipeid PRIMARY KEY (recipeid);


--
-- Name: pk_recipeingredients; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT pk_recipeingredients PRIMARY KEY (recipeid, ingredientid);


--
-- Name: fk_ingredient; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT fk_ingredient FOREIGN KEY (ingredientid) REFERENCES ingredients(ingredientid);


--
-- Name: fk_recipe; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT fk_recipe FOREIGN KEY (recipeid) REFERENCES recipe(recipeid);


--
-- Name: public; Type: ACL; Schema: -; Owner: admin
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM admin;
GRANT ALL ON SCHEMA public TO admin;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

