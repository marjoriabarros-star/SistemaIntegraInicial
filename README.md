# Sistema Integra Inicial

## Status do Projeto
🚀 **SISTEMA COMPLETO COM FEATURES AVANÇADADAS** - Todas as 11 telas implementadas e testadas

## Tecnologias Aplicadas
- **Java** - Linguagem de programação principal
- **Java Swing** - Interface gráfica desktop  
- **MySQL** - Banco de dados relacional
- **JDBC** - Conexão com banco de dados
- **Git/GitHub** - Controle de versão
- **MVC Pattern** - Arquitetura do sistema
- **Java Desktop Integration** - Integração com SO
- **File I/O** - Manipulação avançada de arquivos

## Time de Desenvolvimento
- **Marjori Barros** 

## Objetivo do Software
Sistema de gestão empresarial completo para controle de usuários, processos e documentação interna com sistema hierárquico de permissões robusto e recursos avançados de gerenciamento de arquivos.

---

## 🚀 FUNCIONALIDADES IMPLEMENTADAS - VISÃO COMPLETA

### 🔐 Módulo de Autenticação
- Login seguro com validação no banco de dados
- ✅ Controle de acesso por tipo de usuário
- ✅ Redirecionamento baseado em permissões

### 👥 Módulo de Usuários (COMPLETO)
- ✅ **TelaCadastroUsuario** - Cadastro/edição de usuários
- ✅ **TelaUsuariosCadastrados** - Listagem completa com filtros
- ✅ CRUD Completo com validações avançadas

### 📋 Módulo de Processos (COMPLETO)  
- ✅ **TelaCadastroDeProcessos** - Cadastro/edição de processos
- ✅ **TelaProcessosCadastrados** - Listagem completa com filtros
- ✅ CRUD Completo com organização por setores

### 📚 Módulo de Manuais (COMPLETO COM FEATURES AVANÇADADAS) 🆕
- ✅ **TelaCadastroManuais** - Upload de arquivos com validação
- ✅ **TelaManuaisCadastrados** - 🎯 **GESTÃO AVANÇADA DE DOCUMENTOS**

---

## 🆕 **TELAMANUAISCADASTRADOS.JAVA - FEATURES ESPECIAIS**

### 🎯 Funcionalidades Exclusivas Implementadas:

#### 📤 **Sistema de Download Inteligente**
- ✅ **Download com seletor de local** - Usuário escolhe onde salvar
- ✅ **Preservação de extensão** - Mantém formato original do arquivo
- ✅ **Confirmação de sobrescrita** - Previse perda acidental de arquivos
- ✅ **Abertura automática da pasta** - Opção de abrir local do download

#### 👁️ **Visualização de Documentos**
- ✅ **Duplo-clique para abrir** - Experiência intuitiva
- ✅ **Integração com programas padrão** - Abre com aplicação nativa do SO
- ✅ **Verificação de existência** - Valida se arquivo ainda está disponível

#### 🗑️ **Sistema de Exclusão Segura**
- ✅ **Exclusão em duas etapas** - Arquivo físico + registro no banco
- ✅ **Confirmação de exclusão** - Diálogo de confirmação detalhado
- ✅ **Feedback de operação** - Informa se arquivo físico foi removido
- ✅ **Tratamento de erros** - Lida com arquivos já excluídos

#### 🔍 **Sistema de Filtros Avançados**
- ✅ **Filtro por descrição** - Busca por nome do arquivo
- ✅ **Filtro por setor** - Organização por departamentos
- ✅ **Ordenação por data** - Mais recentes primeiro
- ✅ **Atualização em tempo real** - Filtros aplicados instantaneamente

#### 🛡️ **Sistema de Permissões Granular**
- ✅ **Visualização para todos** - Qualquer usuário pode ver documentos
- ✅ **Download universal** - Todos podem baixar arquivos
- ✅ **Exclusão restrita** - Apenas Admin/Direção/Gerência
- ✅ **Tooltips informativos** - Explica restrições de permissão

---

## 🗃️ ESTRUTURA COMPLETA DO BANCO DE DADOS

### Tabelas Principais:
```sql
-- Usuários do sistema
Usuarios (id_usuario, nome, email, senha, cargo, setor_id)

-- Processos por setor  
Processos (id_processo, descricao, setor_id)

-- Setores da empresa
Setores (id_setor, nome_setor)

-- Documentação e manuais (EXPANDIDA)
Manuais (id_manual, nome_arquivo, setor, caminho_arquivo, tamanho, data_upload)
