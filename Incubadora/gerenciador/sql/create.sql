

--
-- TOC entry 211 (class 1259 OID 41549)
-- Name: acao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE acao (
    id bigint NOT NULL,
    nome character varying(255),
    responsavel character varying(255),
    data_inicio date,
    data_fim date,
    categoria_fk bigint NOT NULL,
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.acao OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 41547)
-- Name: acao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE acao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.acao_id_seq OWNER TO postgres;

--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 210
-- Name: acao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE acao_id_seq OWNED BY acao.id;


--
-- TOC entry 184 (class 1259 OID 41339)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 41308)
-- Name: empreendimento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empreendimento (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    razao_social character varying(255),
    cnpj character varying(25),
    inscricao_estadual character varying(255),
    inscricao_municipal character varying(255),
    email character varying(255),
    fax character varying(255),
    missao character varying(255),
    visao character varying(255),
    valores character varying(255),
    data_ingresso date,
    data_abertura date,
    data_prev_graduacao date,
    path_logo character varying(255),
    ramo_atividade_fk bigint NOT NULL,
    status_fk bigint,
    status character varying(50),
    telefone character varying(255),
    edital_fk bigint NOT NULL,
    contratacao_aceita boolean,
    datahora_apresentacao timestamp without time zone,
    local_apresentacao text
);


ALTER TABLE public.empreendimento OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 41489)
-- Name: orientacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE orientacao (
    id bigint NOT NULL,
    descricao character varying(255),
    categoria_fk bigint NOT NULL
);


ALTER TABLE public.orientacao OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 41653)
-- Name: acao_read_by_id_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW acao_read_by_id_view AS
 SELECT a.id,
    a.nome,
    a.responsavel,
    a.data_inicio,
    a.data_fim,
    a.categoria_fk,
    a.empreendimento_fk,
    e.nome AS e_nome,
    e.razao_social,
    e.id AS e_id,
    e.cnpj,
    e.data_abertura,
    e.data_ingresso,
    e.data_prev_graduacao,
    e.ramo_atividade_fk,
    ca.id AS ca_id,
    ca.nome AS ca_nome,
    o.id AS o_id,
    o.descricao
   FROM (((acao a
   LEFT JOIN categoria ca ON ((ca.id = a.categoria_fk)))
   LEFT JOIN empreendimento e ON ((e.id = a.empreendimento_fk)))
   LEFT JOIN orientacao o ON ((o.categoria_fk = ca.id)));


ALTER TABLE public.acao_read_by_id_view OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 41439)
-- Name: ambiente_externo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ambiente_externo (
    id bigint NOT NULL,
    oportunidade character varying(255),
    ameaca character varying(255),
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.ambiente_externo OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 41437)
-- Name: ambiente_externo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ambiente_externo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ambiente_externo_id_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 197
-- Name: ambiente_externo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ambiente_externo_id_seq OWNED BY ambiente_externo.id;


--
-- TOC entry 206 (class 1259 OID 41502)
-- Name: ambiente_interno; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ambiente_interno (
    id bigint NOT NULL,
    capital_forte character varying(255),
    gestao_forte character varying(255),
    empreendedor_forte character varying(255),
    mercado_forte character varying(255),
    tecnologico_forte character varying(255),
    capital_fraco character varying(255),
    gestao_fraco character varying(255),
    empreendedor_fraco character varying(255),
    mercado_fraco character varying(255),
    tecnologico_fraco character varying(255),
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.ambiente_interno OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 41500)
-- Name: ambiente_interno_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ambiente_interno_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ambiente_interno_id_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 205
-- Name: ambiente_interno_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ambiente_interno_id_seq OWNED BY ambiente_interno.id;


--
-- TOC entry 202 (class 1259 OID 41474)
-- Name: apresentacao_negocio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE apresentacao_negocio (
    empreendimento_fk bigint NOT NULL,
    curriculo text,
    disponibilidade text,
    inovacao_produto text,
    tempo_desenvolvimento text,
    identificacao text,
    mercado_alvo text,
    vantagem_competitiva text,
    investimento text,
    retorno text,
    parcerias text,
    estrutura_organizacional text
);


ALTER TABLE public.apresentacao_negocio OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 41641)
-- Name: arquivo_pratica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arquivo_pratica (
    id bigint NOT NULL,
    pratica_fk bigint NOT NULL,
    path_arquivo character varying(255)
);


ALTER TABLE public.arquivo_pratica OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 41639)
-- Name: arquivo_pratica_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE arquivo_pratica_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.arquivo_pratica_id_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 217
-- Name: arquivo_pratica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE arquivo_pratica_id_seq OWNED BY arquivo_pratica.id;


--
-- TOC entry 172 (class 1259 OID 41245)
-- Name: avaliador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliador (
    usuario_fk bigint NOT NULL,
    cpf character varying(16)
);


ALTER TABLE public.avaliador OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 41568)
-- Name: avaliador_empreendimento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliador_empreendimento (
    avaliador_fk bigint NOT NULL,
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.avaliador_empreendimento OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 41337)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 183
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_id_seq OWNED BY categoria.id;


--
-- TOC entry 190 (class 1259 OID 41384)
-- Name: criterio_avaliacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE criterio_avaliacao (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    ativo boolean,
    eixo_fk bigint NOT NULL
);


ALTER TABLE public.criterio_avaliacao OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 41382)
-- Name: criterio_avaliacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE criterio_avaliacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.criterio_avaliacao_id_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 189
-- Name: criterio_avaliacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE criterio_avaliacao_id_seq OWNED BY criterio_avaliacao.id;


--
-- TOC entry 196 (class 1259 OID 41428)
-- Name: cronograma_anual; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cronograma_anual (
    id bigint NOT NULL,
    atividade character varying(255) NOT NULL,
    data_inicio date NOT NULL,
    data_fim date NOT NULL,
    status character varying(255) NOT NULL,
    comentario character varying(255)
);


ALTER TABLE public.cronograma_anual OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 41426)
-- Name: cronograma_anual_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cronograma_anual_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cronograma_anual_id_seq OWNER TO postgres;

--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 195
-- Name: cronograma_anual_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cronograma_anual_id_seq OWNED BY cronograma_anual.id;


--
-- TOC entry 214 (class 1259 OID 41598)
-- Name: cronograma_gestor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cronograma_gestor (
    cronograma_fk bigint NOT NULL,
    gestor_fk bigint NOT NULL
);


ALTER TABLE public.cronograma_gestor OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 41347)
-- Name: duvida; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE duvida (
    id bigint NOT NULL,
    duvida text NOT NULL,
    data_hora timestamp without time zone NOT NULL,
    empreendedor_fk bigint NOT NULL
);


ALTER TABLE public.duvida OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 41345)
-- Name: duvida_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE duvida_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.duvida_id_seq OWNER TO postgres;

--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 185
-- Name: duvida_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE duvida_id_seq OWNED BY duvida.id;


--
-- TOC entry 176 (class 1259 OID 41284)
-- Name: edital; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE edital (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    data_inicio timestamp without time zone NOT NULL,
    data_fim timestamp without time zone NOT NULL,
    data_prorrogacao timestamp without time zone,
    resumo character varying(255) NOT NULL,
    gestor_fk bigint NOT NULL,
    path_arquivo text
);


ALTER TABLE public.edital OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 41282)
-- Name: edital_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE edital_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.edital_id_seq OWNER TO postgres;

--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 175
-- Name: edital_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE edital_id_seq OWNED BY edital.id;


--
-- TOC entry 182 (class 1259 OID 41331)
-- Name: eixo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE eixo (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    peso double precision NOT NULL
);


ALTER TABLE public.eixo OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 41329)
-- Name: eixo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE eixo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.eixo_id_seq OWNER TO postgres;

--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 181
-- Name: eixo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE eixo_id_seq OWNED BY eixo.id;


--
-- TOC entry 173 (class 1259 OID 41257)
-- Name: empreendedor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empreendedor (
    usuario_fk bigint NOT NULL,
    cpf character varying(15),
    rg character varying(15),
    data_nascimento date,
    escolaridade character varying(255),
    formacao_profissional character varying(255),
    ocupacao character varying(255),
    fax character varying(255)
);


ALTER TABLE public.empreendedor OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 41583)
-- Name: empreendimento_empreendedor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empreendimento_empreendedor (
    empreendedor_fk bigint NOT NULL,
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.empreendimento_empreendedor OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 41306)
-- Name: empreendimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE empreendimento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empreendimento_id_seq OWNER TO postgres;

--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 179
-- Name: empreendimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empreendimento_id_seq OWNED BY empreendimento.id;


--
-- TOC entry 201 (class 1259 OID 41461)
-- Name: endereco_empreendedor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE endereco_empreendedor (
    empreendedor_fk bigint NOT NULL,
    rua character varying(255) NOT NULL,
    bairro character varying(255) NOT NULL,
    numero character varying(50) NOT NULL,
    cidade character varying(255) NOT NULL,
    uf character varying(2)
);


ALTER TABLE public.endereco_empreendedor OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 41420)
-- Name: estagio_evolucao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estagio_evolucao (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.estagio_evolucao OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 41418)
-- Name: estagio_evolucao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estagio_evolucao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estagio_evolucao_id_seq OWNER TO postgres;

--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 193
-- Name: estagio_evolucao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estagio_evolucao_id_seq OWNED BY estagio_evolucao.id;


--
-- TOC entry 174 (class 1259 OID 41270)
-- Name: gestor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gestor (
    usuario_fk bigint NOT NULL,
    cpf character varying(16)
);


ALTER TABLE public.gestor OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 41532)
-- Name: gestor_empreendimento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gestor_empreendimento (
    gestor_fk bigint NOT NULL,
    empreendimento_fk bigint NOT NULL
);


ALTER TABLE public.gestor_empreendimento OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 41397)
-- Name: nota; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nota (
    id bigint NOT NULL,
    avaliador_fk bigint NOT NULL,
    empreendimento_fk bigint NOT NULL,
    nota double precision NOT NULL,
    datahora timestamp without time zone NOT NULL,
    criterio_avaliacao_fk bigint NOT NULL
);


ALTER TABLE public.nota OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 41395)
-- Name: nota_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE nota_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nota_id_seq OWNER TO postgres;

--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 191
-- Name: nota_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE nota_id_seq OWNED BY nota.id;


--
-- TOC entry 203 (class 1259 OID 41487)
-- Name: orientacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orientacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orientacao_id_seq OWNER TO postgres;

--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 203
-- Name: orientacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orientacao_id_seq OWNED BY orientacao.id;


--
-- TOC entry 216 (class 1259 OID 41615)
-- Name: pratica_chave; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pratica_chave (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(255),
    data_evolucao date NOT NULL,
    data_certificacao date NOT NULL,
    estagio_evolucao_fk bigint NOT NULL,
    cronograma_anual_fk bigint NOT NULL,
    processo_fk bigint NOT NULL
);


ALTER TABLE public.pratica_chave OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 41613)
-- Name: pratica_chave_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pratica_chave_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pratica_chave_id_seq OWNER TO postgres;

--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 215
-- Name: pratica_chave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pratica_chave_id_seq OWNED BY pratica_chave.id;


--
-- TOC entry 208 (class 1259 OID 41518)
-- Name: processo_chave; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE processo_chave (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    gestor_fk bigint NOT NULL,
    path_arquivo character varying(512)
);


ALTER TABLE public.processo_chave OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 41516)
-- Name: processo_chave_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE processo_chave_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.processo_chave_id_seq OWNER TO postgres;

--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 207
-- Name: processo_chave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE processo_chave_id_seq OWNED BY processo_chave.id;


--
-- TOC entry 178 (class 1259 OID 41300)
-- Name: ramo_atividade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ramo_atividade (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.ramo_atividade OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 41298)
-- Name: ramo_atividade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ramo_atividade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ramo_atividade_id_seq OWNER TO postgres;

--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 177
-- Name: ramo_atividade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ramo_atividade_id_seq OWNED BY ramo_atividade.id;


--
-- TOC entry 188 (class 1259 OID 41363)
-- Name: resposta_duvida; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resposta_duvida (
    id bigint NOT NULL,
    resposta text NOT NULL,
    data_hora timestamp without time zone NOT NULL,
    duvida_fk bigint NOT NULL,
    usuario_fk bigint NOT NULL
);


ALTER TABLE public.resposta_duvida OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 41361)
-- Name: resposta_duvida_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resposta_duvida_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resposta_duvida_id_seq OWNER TO postgres;

--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 187
-- Name: resposta_duvida_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resposta_duvida_id_seq OWNED BY resposta_duvida.id;


--
-- TOC entry 200 (class 1259 OID 41455)
-- Name: status_empreendimento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE status_empreendimento (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    data_hora timestamp without time zone NOT NULL
);


ALTER TABLE public.status_empreendimento OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 41453)
-- Name: status_empreendimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE status_empreendimento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_empreendimento_id_seq OWNER TO postgres;

--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 199
-- Name: status_empreendimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE status_empreendimento_id_seq OWNED BY status_empreendimento.id;


--
-- TOC entry 171 (class 1259 OID 41234)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    senha character varying(250) NOT NULL,
    telefone character varying(15),
    tipo_usuario character varying(255) NOT NULL,
    sobrenome character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 41232)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 170
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 2008 (class 2604 OID 41552)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acao ALTER COLUMN id SET DEFAULT nextval('acao_id_seq'::regclass);


--
-- TOC entry 2003 (class 2604 OID 41442)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambiente_externo ALTER COLUMN id SET DEFAULT nextval('ambiente_externo_id_seq'::regclass);


--
-- TOC entry 2006 (class 2604 OID 41505)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambiente_interno ALTER COLUMN id SET DEFAULT nextval('ambiente_interno_id_seq'::regclass);


--
-- TOC entry 2010 (class 2604 OID 41644)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arquivo_pratica ALTER COLUMN id SET DEFAULT nextval('arquivo_pratica_id_seq'::regclass);


--
-- TOC entry 1996 (class 2604 OID 41342)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN id SET DEFAULT nextval('categoria_id_seq'::regclass);


--
-- TOC entry 1999 (class 2604 OID 41387)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY criterio_avaliacao ALTER COLUMN id SET DEFAULT nextval('criterio_avaliacao_id_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 41431)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cronograma_anual ALTER COLUMN id SET DEFAULT nextval('cronograma_anual_id_seq'::regclass);


--
-- TOC entry 1997 (class 2604 OID 41350)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY duvida ALTER COLUMN id SET DEFAULT nextval('duvida_id_seq'::regclass);


--
-- TOC entry 1992 (class 2604 OID 41287)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY edital ALTER COLUMN id SET DEFAULT nextval('edital_id_seq'::regclass);


--
-- TOC entry 1995 (class 2604 OID 41334)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY eixo ALTER COLUMN id SET DEFAULT nextval('eixo_id_seq'::regclass);


--
-- TOC entry 1994 (class 2604 OID 41311)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendimento ALTER COLUMN id SET DEFAULT nextval('empreendimento_id_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 41423)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estagio_evolucao ALTER COLUMN id SET DEFAULT nextval('estagio_evolucao_id_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 41400)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota ALTER COLUMN id SET DEFAULT nextval('nota_id_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 41492)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orientacao ALTER COLUMN id SET DEFAULT nextval('orientacao_id_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 41618)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pratica_chave ALTER COLUMN id SET DEFAULT nextval('pratica_chave_id_seq'::regclass);


--
-- TOC entry 2007 (class 2604 OID 41521)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo_chave ALTER COLUMN id SET DEFAULT nextval('processo_chave_id_seq'::regclass);


--
-- TOC entry 1993 (class 2604 OID 41303)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ramo_atividade ALTER COLUMN id SET DEFAULT nextval('ramo_atividade_id_seq'::regclass);


--
-- TOC entry 1998 (class 2604 OID 41366)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resposta_duvida ALTER COLUMN id SET DEFAULT nextval('resposta_duvida_id_seq'::regclass);


--
-- TOC entry 2004 (class 2604 OID 41458)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY status_empreendimento ALTER COLUMN id SET DEFAULT nextval('status_empreendimento_id_seq'::regclass);


--
-- TOC entry 1991 (class 2604 OID 41237)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2066 (class 2606 OID 41557)
-- Name: acao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY acao
    ADD CONSTRAINT acao_pkey PRIMARY KEY (id);


--
-- TOC entry 2050 (class 2606 OID 41447)
-- Name: ambiente_externo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ambiente_externo
    ADD CONSTRAINT ambiente_externo_pkey PRIMARY KEY (id);


--
-- TOC entry 2060 (class 2606 OID 41510)
-- Name: ambiente_interno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ambiente_interno
    ADD CONSTRAINT ambiente_interno_pkey PRIMARY KEY (id);


--
-- TOC entry 2056 (class 2606 OID 41481)
-- Name: apresentacao_negocio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY apresentacao_negocio
    ADD CONSTRAINT apresentacao_negocio_pkey PRIMARY KEY (empreendimento_fk);


--
-- TOC entry 2076 (class 2606 OID 41646)
-- Name: arquivo_pratica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arquivo_pratica
    ADD CONSTRAINT arquivo_pratica_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 41251)
-- Name: avaliador_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliador
    ADD CONSTRAINT avaliador_cpf_key UNIQUE (cpf);


--
-- TOC entry 2068 (class 2606 OID 41572)
-- Name: avaliador_empreendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliador_empreendimento
    ADD CONSTRAINT avaliador_empreendimento_pkey PRIMARY KEY (avaliador_fk, empreendimento_fk);


--
-- TOC entry 2018 (class 2606 OID 41249)
-- Name: avaliador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliador
    ADD CONSTRAINT avaliador_pkey PRIMARY KEY (usuario_fk);


--
-- TOC entry 2036 (class 2606 OID 41344)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2042 (class 2606 OID 41389)
-- Name: criterio_avaliacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY criterio_avaliacao
    ADD CONSTRAINT criterio_avaliacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2048 (class 2606 OID 41436)
-- Name: cronograma_anual_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cronograma_anual
    ADD CONSTRAINT cronograma_anual_pkey PRIMARY KEY (id);


--
-- TOC entry 2072 (class 2606 OID 41602)
-- Name: cronograma_gestor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cronograma_gestor
    ADD CONSTRAINT cronograma_gestor_pkey PRIMARY KEY (cronograma_fk, gestor_fk);


--
-- TOC entry 2038 (class 2606 OID 41355)
-- Name: duvida_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY duvida
    ADD CONSTRAINT duvida_pkey PRIMARY KEY (id);


--
-- TOC entry 2026 (class 2606 OID 41292)
-- Name: edital_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY edital
    ADD CONSTRAINT edital_pkey PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 41336)
-- Name: eixo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY eixo
    ADD CONSTRAINT eixo_pkey PRIMARY KEY (id);


--
-- TOC entry 2020 (class 2606 OID 41264)
-- Name: empreendedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empreendedor
    ADD CONSTRAINT empreendedor_pkey PRIMARY KEY (usuario_fk);


--
-- TOC entry 2030 (class 2606 OID 41318)
-- Name: empreendimento_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empreendimento
    ADD CONSTRAINT empreendimento_email_key UNIQUE (email);


--
-- TOC entry 2070 (class 2606 OID 41587)
-- Name: empreendimento_empreendedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empreendimento_empreendedor
    ADD CONSTRAINT empreendimento_empreendedor_pkey PRIMARY KEY (empreendedor_fk, empreendimento_fk);


--
-- TOC entry 2032 (class 2606 OID 41316)
-- Name: empreendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empreendimento
    ADD CONSTRAINT empreendimento_pkey PRIMARY KEY (id);


--
-- TOC entry 2054 (class 2606 OID 41468)
-- Name: endereco_empreendedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY endereco_empreendedor
    ADD CONSTRAINT endereco_empreendedor_pkey PRIMARY KEY (empreendedor_fk);


--
-- TOC entry 2046 (class 2606 OID 41425)
-- Name: estagio_evolucao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estagio_evolucao
    ADD CONSTRAINT estagio_evolucao_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 41276)
-- Name: gestor_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gestor
    ADD CONSTRAINT gestor_cpf_key UNIQUE (cpf);


--
-- TOC entry 2064 (class 2606 OID 41536)
-- Name: gestor_empreendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gestor_empreendimento
    ADD CONSTRAINT gestor_empreendimento_pkey PRIMARY KEY (gestor_fk, empreendimento_fk);


--
-- TOC entry 2024 (class 2606 OID 41274)
-- Name: gestor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gestor
    ADD CONSTRAINT gestor_pkey PRIMARY KEY (usuario_fk);


--
-- TOC entry 2044 (class 2606 OID 41402)
-- Name: nota_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nota
    ADD CONSTRAINT nota_pkey PRIMARY KEY (id);


--
-- TOC entry 2058 (class 2606 OID 41494)
-- Name: orientacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orientacao
    ADD CONSTRAINT orientacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2074 (class 2606 OID 41623)
-- Name: pratica_chave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pratica_chave
    ADD CONSTRAINT pratica_chave_pkey PRIMARY KEY (id);


--
-- TOC entry 2062 (class 2606 OID 41526)
-- Name: processo_chave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY processo_chave
    ADD CONSTRAINT processo_chave_pkey PRIMARY KEY (id);


--
-- TOC entry 2028 (class 2606 OID 41305)
-- Name: ramo_atividade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ramo_atividade
    ADD CONSTRAINT ramo_atividade_pkey PRIMARY KEY (id);


--
-- TOC entry 2040 (class 2606 OID 41371)
-- Name: resposta_duvida_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resposta_duvida
    ADD CONSTRAINT resposta_duvida_pkey PRIMARY KEY (id);


--
-- TOC entry 2052 (class 2606 OID 41460)
-- Name: status_empreendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY status_empreendimento
    ADD CONSTRAINT status_empreendimento_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 41244)
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 2014 (class 2606 OID 41242)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2098 (class 2606 OID 41558)
-- Name: acao_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acao
    ADD CONSTRAINT acao_categoria_fk FOREIGN KEY (categoria_fk) REFERENCES categoria(id) ON DELETE CASCADE;


--
-- TOC entry 2099 (class 2606 OID 41563)
-- Name: acao_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acao
    ADD CONSTRAINT acao_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2090 (class 2606 OID 41448)
-- Name: ambiente_externo_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambiente_externo
    ADD CONSTRAINT ambiente_externo_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2094 (class 2606 OID 41511)
-- Name: ambiente_interno_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambiente_interno
    ADD CONSTRAINT ambiente_interno_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2092 (class 2606 OID 41482)
-- Name: apresentacao_negocio_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY apresentacao_negocio
    ADD CONSTRAINT apresentacao_negocio_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2109 (class 2606 OID 41647)
-- Name: arquivo_pratica_pratica_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arquivo_pratica
    ADD CONSTRAINT arquivo_pratica_pratica_fk FOREIGN KEY (pratica_fk) REFERENCES pratica_chave(id) ON DELETE CASCADE;


--
-- TOC entry 2100 (class 2606 OID 41573)
-- Name: avaliador_empreendimento_avaliador_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliador_empreendimento
    ADD CONSTRAINT avaliador_empreendimento_avaliador_fk FOREIGN KEY (avaliador_fk) REFERENCES avaliador(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2101 (class 2606 OID 41578)
-- Name: avaliador_empreendimento_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliador_empreendimento
    ADD CONSTRAINT avaliador_empreendimento_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2077 (class 2606 OID 41252)
-- Name: avaliador_usuario_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliador
    ADD CONSTRAINT avaliador_usuario_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON DELETE CASCADE;


--
-- TOC entry 2086 (class 2606 OID 41390)
-- Name: criterio_avaliacao_eixo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY criterio_avaliacao
    ADD CONSTRAINT criterio_avaliacao_eixo_fk FOREIGN KEY (eixo_fk) REFERENCES eixo(id) ON DELETE CASCADE;


--
-- TOC entry 2104 (class 2606 OID 41603)
-- Name: cronograma_gestor_cronograma_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cronograma_gestor
    ADD CONSTRAINT cronograma_gestor_cronograma_fk FOREIGN KEY (cronograma_fk) REFERENCES cronograma_anual(id) ON DELETE CASCADE;


--
-- TOC entry 2105 (class 2606 OID 41608)
-- Name: cronograma_gestor_gestor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cronograma_gestor
    ADD CONSTRAINT cronograma_gestor_gestor_fk FOREIGN KEY (gestor_fk) REFERENCES gestor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2083 (class 2606 OID 41356)
-- Name: duvida_empreendedor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY duvida
    ADD CONSTRAINT duvida_empreendedor_fk FOREIGN KEY (empreendedor_fk) REFERENCES empreendedor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2080 (class 2606 OID 41293)
-- Name: edital_gestor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY edital
    ADD CONSTRAINT edital_gestor_fk FOREIGN KEY (gestor_fk) REFERENCES gestor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2078 (class 2606 OID 41265)
-- Name: empreendedor_usuario_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendedor
    ADD CONSTRAINT empreendedor_usuario_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON DELETE CASCADE;


--
-- TOC entry 2081 (class 2606 OID 41319)
-- Name: empreendimento_edital_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendimento
    ADD CONSTRAINT empreendimento_edital_fk FOREIGN KEY (edital_fk) REFERENCES edital(id) ON DELETE CASCADE;


--
-- TOC entry 2102 (class 2606 OID 41588)
-- Name: empreendimento_empreendedor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendimento_empreendedor
    ADD CONSTRAINT empreendimento_empreendedor_fk FOREIGN KEY (empreendedor_fk) REFERENCES empreendedor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2103 (class 2606 OID 41593)
-- Name: empreendimento_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendimento_empreendedor
    ADD CONSTRAINT empreendimento_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2082 (class 2606 OID 41324)
-- Name: empreendimento_ramo_atividade_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empreendimento
    ADD CONSTRAINT empreendimento_ramo_atividade_fk FOREIGN KEY (ramo_atividade_fk) REFERENCES ramo_atividade(id);


--
-- TOC entry 2091 (class 2606 OID 41469)
-- Name: endereco_empreendedor_empreendedor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco_empreendedor
    ADD CONSTRAINT endereco_empreendedor_empreendedor_fk FOREIGN KEY (empreendedor_fk) REFERENCES empreendedor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2096 (class 2606 OID 41537)
-- Name: gestor_empreendimento_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gestor_empreendimento
    ADD CONSTRAINT gestor_empreendimento_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2097 (class 2606 OID 41542)
-- Name: gestor_empreendimento_gestor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gestor_empreendimento
    ADD CONSTRAINT gestor_empreendimento_gestor_fk FOREIGN KEY (gestor_fk) REFERENCES gestor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2079 (class 2606 OID 41277)
-- Name: gestor_usuario_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gestor
    ADD CONSTRAINT gestor_usuario_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON DELETE CASCADE;


--
-- TOC entry 2087 (class 2606 OID 41403)
-- Name: nota_avaliador_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota
    ADD CONSTRAINT nota_avaliador_fk FOREIGN KEY (avaliador_fk) REFERENCES avaliador(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2088 (class 2606 OID 41408)
-- Name: nota_criterio_avaliacao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota
    ADD CONSTRAINT nota_criterio_avaliacao_fk FOREIGN KEY (criterio_avaliacao_fk) REFERENCES criterio_avaliacao(id) ON DELETE CASCADE;


--
-- TOC entry 2089 (class 2606 OID 41413)
-- Name: nota_empreendimento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota
    ADD CONSTRAINT nota_empreendimento_fk FOREIGN KEY (empreendimento_fk) REFERENCES empreendimento(id) ON DELETE CASCADE;


--
-- TOC entry 2093 (class 2606 OID 41495)
-- Name: orientacao_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orientacao
    ADD CONSTRAINT orientacao_categoria_fk FOREIGN KEY (categoria_fk) REFERENCES categoria(id);


--
-- TOC entry 2106 (class 2606 OID 41624)
-- Name: pratica_chave_cronograma_anual_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pratica_chave
    ADD CONSTRAINT pratica_chave_cronograma_anual_fk FOREIGN KEY (cronograma_anual_fk) REFERENCES cronograma_anual(id);


--
-- TOC entry 2107 (class 2606 OID 41629)
-- Name: pratica_chave_estagio_evolucao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pratica_chave
    ADD CONSTRAINT pratica_chave_estagio_evolucao_fk FOREIGN KEY (estagio_evolucao_fk) REFERENCES estagio_evolucao(id);


--
-- TOC entry 2108 (class 2606 OID 41634)
-- Name: pratica_chave_processo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pratica_chave
    ADD CONSTRAINT pratica_chave_processo_fk FOREIGN KEY (processo_fk) REFERENCES processo_chave(id) ON DELETE CASCADE;


--
-- TOC entry 2095 (class 2606 OID 41527)
-- Name: processo_chave_gestor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo_chave
    ADD CONSTRAINT processo_chave_gestor_fk FOREIGN KEY (gestor_fk) REFERENCES gestor(usuario_fk) ON DELETE CASCADE;


--
-- TOC entry 2084 (class 2606 OID 41372)
-- Name: resposta_duvida_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resposta_duvida
    ADD CONSTRAINT resposta_duvida_fk FOREIGN KEY (duvida_fk) REFERENCES duvida(id) ON DELETE CASCADE;


--
-- TOC entry 2085 (class 2606 OID 41377)
-- Name: resposta_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resposta_duvida
    ADD CONSTRAINT resposta_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON DELETE CASCADE;


--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-10-06 17:09:02

--
-- PostgreSQL database dump complete
--

