--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.16
-- Dumped by pg_dump version 9.6.16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: priorities; Type: TABLE; Schema: public; Owner: student
--

CREATE TABLE public.priorities (
    code integer NOT NULL,
    rank text
);


ALTER TABLE public.priorities OWNER TO student;

--
-- Name: priorities_code_seq; Type: SEQUENCE; Schema: public; Owner: student
--

CREATE SEQUENCE public.priorities_code_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.priorities_code_seq OWNER TO student;

--
-- Name: priorities_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: student
--

ALTER SEQUENCE public.priorities_code_seq OWNED BY public.priorities.code;


--
-- Name: tasks; Type: TABLE; Schema: public; Owner: student
--

CREATE TABLE public.tasks (
    code integer NOT NULL,
    entry_at text,
    done_at text,
    priority_id integer,
    type_id integer,
    is_done_code integer,
    content text,
    message text,
    is_delete integer
);


ALTER TABLE public.tasks OWNER TO student;

--
-- Name: tasks_code_seq; Type: SEQUENCE; Schema: public; Owner: student
--

CREATE SEQUENCE public.tasks_code_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_code_seq OWNER TO student;

--
-- Name: tasks_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: student
--

ALTER SEQUENCE public.tasks_code_seq OWNED BY public.tasks.code;


--
-- Name: types; Type: TABLE; Schema: public; Owner: student
--

CREATE TABLE public.types (
    code integer NOT NULL,
    name text
);


ALTER TABLE public.types OWNER TO student;

--
-- Name: types_code_seq; Type: SEQUENCE; Schema: public; Owner: student
--

CREATE SEQUENCE public.types_code_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.types_code_seq OWNER TO student;

--
-- Name: types_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: student
--

ALTER SEQUENCE public.types_code_seq OWNED BY public.types.code;


--
-- Name: priorities code; Type: DEFAULT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.priorities ALTER COLUMN code SET DEFAULT nextval('public.priorities_code_seq'::regclass);


--
-- Name: tasks code; Type: DEFAULT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.tasks ALTER COLUMN code SET DEFAULT nextval('public.tasks_code_seq'::regclass);


--
-- Name: types code; Type: DEFAULT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.types ALTER COLUMN code SET DEFAULT nextval('public.types_code_seq'::regclass);


--
-- Data for Name: priorities; Type: TABLE DATA; Schema: public; Owner: student
--

COPY public.priorities (code, rank) FROM stdin;
1	緊急
2	重要
3	普通
\.


--
-- Name: priorities_code_seq; Type: SEQUENCE SET; Schema: public; Owner: student
--

SELECT pg_catalog.setval('public.priorities_code_seq', 3, true);


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: student
--

COPY public.tasks (code, entry_at, done_at, priority_id, type_id, is_done_code, content, message, is_delete) FROM stdin;
57	\N	2020-05-27	2	1	0	3	\N	1
54	\N	2020-05-28	2	1	0		\N	1
55	\N	2020-05-30	3	1	1	44	\N	0
58	\N		3	1	1		\N	1
60	\N	2020-05-27	2	3	1	44	\N	0
52	\N	2020-05-26	2	1	0	1いいいああああ	\N	0
59	\N	2020-05-26	2	1	0	1ｊｊｊ	\N	0
63	\N	2020-05-25	2	1	0	あああ	\N	0
61	\N	2020-05-19	1	2	1	yyyyyy	\N	0
51	\N	2020-05-25	2	1	0	いいい	\N	1
64	\N		2	1	0		\N	1
56	\N	2020-05-27	2	1	1	2	\N	0
65	\N		2	1	0		\N	1
66	\N		2	1	0		\N	1
67	\N		2	1	0		\N	1
62	\N	2020-05-29	2	2	0	あああ	\N	0
69	\N	2020-06-03	2	1	0	あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ	\N	1
68	\N	2020-05-07	2	1	0	aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa	\N	1
70	\N		2	1	0		\N	0
71	\N		2	1	0		\N	0
\.


--
-- Name: tasks_code_seq; Type: SEQUENCE SET; Schema: public; Owner: student
--

SELECT pg_catalog.setval('public.tasks_code_seq', 71, true);


--
-- Data for Name: types; Type: TABLE DATA; Schema: public; Owner: student
--

COPY public.types (code, name) FROM stdin;
1	日常
2	スポーツ
3	仕事
\.


--
-- Name: types_code_seq; Type: SEQUENCE SET; Schema: public; Owner: student
--

SELECT pg_catalog.setval('public.types_code_seq', 1, true);


--
-- Name: priorities priorities_pkey; Type: CONSTRAINT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.priorities
    ADD CONSTRAINT priorities_pkey PRIMARY KEY (code);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (code);


--
-- Name: types types_pkey; Type: CONSTRAINT; Schema: public; Owner: student
--

ALTER TABLE ONLY public.types
    ADD CONSTRAINT types_pkey PRIMARY KEY (code);


--
-- PostgreSQL database dump complete
--

