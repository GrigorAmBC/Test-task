--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3 (Ubuntu 12.3-1.pgdg20.04+1)
-- Dumped by pg_dump version 12.3 (Ubuntu 12.3-1.pgdg20.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Customer" (
    customer_id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL
);


ALTER TABLE public."Customer" OWNER TO postgres;

--
-- Name: Product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Product" (
    product_id integer NOT NULL,
    product_name character varying(60) NOT NULL,
    product_price integer NOT NULL
);


ALTER TABLE public."Product" OWNER TO postgres;

--
-- Name: Sale; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Sale" (
    sale_id integer NOT NULL,
    customer_id integer NOT NULL,
    order_date date NOT NULL
);


ALTER TABLE public."Sale" OWNER TO postgres;

--
-- Name: SaleDetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."SaleDetail" (
    sale_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public."SaleDetail" OWNER TO postgres;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_customer_id_seq OWNER TO postgres;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public."Customer".customer_id;


--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_product_id_seq OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public."Product".product_id;


--
-- Name: sale_sale_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sale_sale_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sale_sale_id_seq OWNER TO postgres;

--
-- Name: sale_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sale_sale_id_seq OWNED BY public."Sale".sale_id;


--
-- Name: Customer customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Customer" ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);


--
-- Name: Product product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Product" ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- Name: Sale sale_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Sale" ALTER COLUMN sale_id SET DEFAULT nextval('public.sale_sale_id_seq'::regclass);


--
-- Data for Name: Customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Customer" (customer_id, first_name, last_name) FROM stdin;
1	Григор	Абгарян
2	Никита	Смирнов
3	Денис	Гришин
4	Андрей	Карпов
5	Павел	Павлов
6	Иван	Смирнов
\.


--
-- Data for Name: Product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Product" (product_id, product_name, product_price) FROM stdin;
1	Сметана	65
2	Колбаса	250
3	Хлеб	33
4	Минеральная вода	40
5	Йогурт	60
\.


--
-- Data for Name: Sale; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Sale" (sale_id, customer_id, order_date) FROM stdin;
1	2	2020-06-25
2	4	2020-06-19
3	2	2020-06-15
4	2	2020-06-20
5	3	2020-06-18
6	3	2020-06-27
7	5	2020-06-28
8	3	2020-06-28
9	5	2020-06-23
10	2	2020-06-19
11	4	2020-06-28
12	5	2020-06-16
13	5	2020-06-30
14	4	2020-06-30
15	4	2020-06-25
16	2	2020-06-29
17	5	2020-06-23
18	3	2020-06-25
19	3	2020-06-29
20	6	2020-06-23
\.


--
-- Data for Name: SaleDetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."SaleDetail" (sale_id, product_id, quantity) FROM stdin;
1	4	7
2	3	4
3	4	5
4	1	12
5	3	13
6	2	11
7	4	3
8	1	19
9	1	5
10	5	6
11	1	1
12	4	19
13	4	18
14	2	10
15	3	8
16	2	10
17	1	19
18	1	14
19	2	8
20	3	7
\.


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 1, false);


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_product_id_seq', 1, false);


--
-- Name: sale_sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sale_sale_id_seq', 1, false);


--
-- Name: Customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Customer"
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: Product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: SaleDetail sale_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SaleDetail"
    ADD CONSTRAINT sale_detail_pkey PRIMARY KEY (sale_id, product_id);


--
-- Name: Sale sale_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Sale"
    ADD CONSTRAINT sale_pkey PRIMARY KEY (sale_id);


--
-- Name: Sale sale_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Sale"
    ADD CONSTRAINT sale_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public."Customer"(customer_id) NOT VALID;


--
-- Name: SaleDetail sale_detail_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SaleDetail"
    ADD CONSTRAINT sale_detail_product_id_fkey FOREIGN KEY (product_id) REFERENCES public."Product"(product_id) NOT VALID;


--
-- Name: SaleDetail sale_detail_sale_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."SaleDetail"
    ADD CONSTRAINT sale_detail_sale_id_fkey FOREIGN KEY (sale_id) REFERENCES public."Sale"(sale_id) NOT VALID;


--
-- PostgreSQL database dump complete
--

