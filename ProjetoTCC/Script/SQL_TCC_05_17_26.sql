Create database PROJETO_TCC_PRODENTSYS
GO

USE PROJETO_TCC_PRODENTSYS
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

--Criação de tabela - Funcionario
CREATE TABLE dbo.tbFuncionario(
numFunc integer identity not null,
nomeFunc varchar(65) not null,
telFunc char(15) not null,
profissaoFunc varchar(30) not null,
login_funcionario varchar(30) not NULL,
senha_funcionario nvarchar(max) not NULL,
primary key(numFunc)
)
go

alter table tbFuncionario
add salarioFunc numeric(12,2) not null
go

alter table tbFuncionario
add comissaoFunc numeric(12,2) null
go

SET ANSI_PADDING OFF
GO
--Criação de tabela - Cliente
create table tbCliente(
numCliente integer identity primary key,
nomeCliente varchar(65) not null,
cpfCliente char(14) not null,
telCliente char(15) not null,
ruaCliente varchar(40) not null,
numEndCliente varchar(4) not null,
bairroCliente varchar(25) not null,
cidadeCliente varchar(30) not null,
cepCliente char(9) not null,
emailCliente varchar(60) not null
)
go

--Criação de tabela - Pedido
create table tbPedido(
numPed integer identity not null primary key,
nomeCliente varchar(65) not null,
numCliente integer not null,
nomePaciente varchar(65) not null,
dataPedido smalldatetime not null,
dataEntrega smalldatetime not null,
tipoProtese varchar(max) not null,
nomeProtese varchar(max) not null,
--precoProtese numeric(10,2) null,
--numProtese integer null,
nomeFunc varchar(65) not null,
numFunc integer not null,
totalPedido numeric(12,2) null,
constraint FK_numCliente foreign key (numCliente) references tbCliente,
constraint FK_numFunc foreign key (numFunc) references tbFuncionario
--constraint FK_numProtese foreign key (numProtese) references tbTabeladePrecos

)
go

alter table tbPedido
add observacoesPed varchar(max) null

alter table tbPedido
add cpfCliente char(14) not null --mudar pra not null

--Criação de tabela - Fornecedor
create table tbFornecedor(
numFornec integer identity not null,
nomeFornec varchar(65) not null,
telFornec char(15) not null,
ruaFornec varchar(40) not null,
numEndFornec varchar(4) not null,
bairroFornec varchar(25) not null,
cidadeFornec varchar(30) not null,
cepFornec char(9) not null
primary key(numFornec)
)
go


--Criação de tabela - Folha de Pagamento
create table tbFolhadePagamento(
codDep int identity not null,
numFunc integer not null,
nomeFunc varchar(65),
salarioFunc numeric(12,2),
comissaoFuncTotal numeric(12,2),
bonusFunc numeric(12,2),
totalFunc numeric(12,2),
profissaoFunc varchar(15)
constraint PK_codDep primary key (codDep),
constraint FK_numFuncionario foreign key (numFunc) references tbFuncionario)
go

--ALTER TABLE tbFolhadePagamento DROP comissaoFunc


--Deletando uma coluna
--ALTER TABLE (NOME DA TABELA) DROP (NOME DA COLUNA)

--Deletando uma tabela
--DROP TABLE (nome tabela)


--Criação de tabela - Tabela de Preoços
create table tbTabeladePrecos(
tipoProtese varchar(40) not null,
nomeProtese varchar(40) not null,
precoProtese numeric(10,2) not null,
numProtese integer identity not null
primary key(numProtese)
)
go


--INDICES
create Index idx_nome_cliente on tbCliente (nomeCliente asc)
go
create Index idx_nome_funcionario on tbFuncionario (nomeFunc asc)
go
create Index idx_nome_fornecedor on tbFornecedor (nomeFornec asc)
go
create Index idx_nome_FolhadePagamento on tbFolhadePagamento (nomeFunc asc)
go
create Index idx_nome_Pedido on tbPedido (nomeCliente asc)
go
create Index idx_nome_protese on tbTabeladePrecos (nomeProtese asc)
go


--Abaixo tabelas não usadas

--Criação de tabela - Tabela de Preoços Nair
create table tbTabeladePrecosSeg( ---PrecosSeg ou PrecosDois
numProteseSeg integer identity not null,
nomeProteseSeg varchar(40) not null,
tipoProteseSeg varchar(40) not null,
precoProteseSeg numeric(10,2) not null
primary key(numProteseSeg)
)
go

create table tbAgenda(
numVisitante integer identity not null,
nomeVisitante varchar(50) not null,
--data varchar(10) not null,
data varchar(max) not null,
hora varchar(max) not null
primary key(numVisitante)
)
go

--Criação de tabela - Pessoa
create table tbPessoa(
numCliente integer not null,
nomeCliente varchar(65),
cpfCliente char(14) unique,
telCliente char(15),
ruaCliente varchar(40),
numEndCliente char(4),
bairroCliente varchar(25),
cidadeCliente varchar(30),
cepCliente varchar(9),
nomeFornec varchar(65) not null,
numFornec integer identity not null,
telFornec varchar(15),
ruaFornec varchar(40),
numEndFornec varchar(4),
bairroFornec varchar(25),
cidadeFornec varchar(30),
cepFornec varchar(9),
numFunc integer not null,
nomeFunc varchar(65),
telFunc varchar(15),
profissaoFunc varchar(15),
salarioFunc varchar(15),
tipoProtese varchar(40),
nomeProtese varchar(40),
precoProtese varchar(9),
numProtese integer not null,
primary key(numCliente)
)
go