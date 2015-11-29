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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ingredients; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE ingredients (
    ingredientid numeric NOT NULL,
    ingredientname text
);


--
-- Name: recipe; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE recipe (
    recipeid numeric(12,0) NOT NULL,
    recipename text,
    recipetype text
);


--
-- Name: recipeingredients; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE recipeingredients (
    recipeid numeric(12,0) NOT NULL,
    ingredientid numeric(12,0) NOT NULL,
    recingcount numeric(6,2),
    recingmeasuretype text
);


--
-- Name: pk_ingredientid; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY ingredients
    ADD CONSTRAINT pk_ingredientid PRIMARY KEY (ingredientid);


--
-- Name: pk_recipeid; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY recipe
    ADD CONSTRAINT pk_recipeid PRIMARY KEY (recipeid);


--
-- Name: pk_recipeingredients; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT pk_recipeingredients PRIMARY KEY (recipeid, ingredientid);


--
-- Name: fk_ingredient; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT fk_ingredient FOREIGN KEY (ingredientid) REFERENCES ingredients(ingredientid);


--
-- Name: fk_recipe; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recipeingredients
    ADD CONSTRAINT fk_recipe FOREIGN KEY (recipeid) REFERENCES recipe(recipeid);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM admin;
GRANT ALL ON SCHEMA public TO admin;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

