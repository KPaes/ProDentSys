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
login_funcionario varchar(30) NULL,
senha_funcionario nvarchar(max) NULL,
primary key(numFunc)
)
go

alter table tbFuncionario
add salarioFunc numeric(12,2) not null
go

alter table tbFuncionario
add comissaoFunc numeric(5,2) null
go

alter table tbFuncionario
add situacaoFunc char(1) null
go

SET ANSI_PADDING OFF
GO

--Criação de tabela - Cliente
create table tbCliente(
numCliente integer identity primary key,
nomeCliente varchar(65) not null,
cpfCliente char(14) unique not null,
telCliente char(15) not null,
ruaCliente varchar(40) not null,
numEndCliente varchar(4) not null,
bairroCliente varchar(25) not null,
cidadeCliente varchar(30) not null,
cepCliente char(9) not null,
emailCliente varchar(60),
complCliente varchar(140)
)
go

alter table tbCliente
add situacaoCliente char(1) null
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
nomeFunc varchar(65) not null,
numFunc integer not null,
totalPedido numeric(12,2) null,
constraint FK_numCliente foreign key (numCliente) references tbCliente,
constraint FK_numFunc foreign key (numFunc) references tbFuncionario
)
go

alter table tbPedido
add observacoesPed varchar(max) null
go

alter table tbPedido
add cpfCliente char(14) not null 
go

alter table tbPedido
add situacaoPed char(1)
go


--Criação de tabela - Fornecedor
create table tbFornecedor(
numFornec integer identity not null,
nomeFornec varchar(65) not null,
telFornec char(15) not null,
ruaFornec varchar(40) not null,
numEndFornec varchar(4) not null,
bairroFornec varchar(25) not null,
cidadeFornec varchar(30) not null,
cepFornec char(9) not null,
complFornec varchar(140) null
primary key(numFornec)
)
go

alter table tbFornecedor
add situacaoFornec char(1) null
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
profissaoFunc varchar(15),
dataInicio smalldatetime  not null,
dataFim smalldatetime  not null
constraint PK_codDep primary key (codDep),
constraint FK_numFuncionario foreign key (numFunc) references tbFuncionario)
go

alter table tbFolhadePagamento
add situacaoFolha char(1)
go


--Criação de tabela - Tabela de Preoços
create table tbTabeladePrecos(
tipoProtese varchar(40) not null,
nomeProtese varchar(40) not null,
precoProtese numeric(10,2) not null,
numProtese integer identity not null
primary key(numProtese)
)
go

alter table tbTabeladePrecos
add situacaoProtese char(1)
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


---===NÃO EXECUTE AS LINHAS ABAIXO==========

--DROP TABLE tbFolhadePagamento


--Deletando uma coluna
--ALTER TABLE (NOME DA TABELA) DROP (NOME DA COLUNA)

--Deletando uma tabela
--DROP TABLE tbPedido

--precoProtese numeric(10,2) null,
--numProtese integer null,
--constraint FK_numProtese foreign key (numProtese) references tbTabeladePrecos
