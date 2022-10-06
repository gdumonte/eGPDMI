# The ONE

The Opportunistic Network Environment simulator.

For introduction and releases, see [the ONE homepage at GitHub](http://akeranen.github.io/the-one/).

For instructions on how to get started, see [the README](https://github.com/akeranen/the-one/wiki/README).

The [wiki page](https://github.com/akeranen/the-one/wiki) has the latest information.

Sobre as modificacoes e configuracoes do TheOne para simulacao usando eGPDMI

Classe GPDMI -> Resposavel por gerar o agrupamento dos nos por interesse e energia 
atraves do input de arquivos de treinamento executa os algoritmos K-Means e EM.

Classe ActiveRouter -> o metodo startTransfer foi alterado para fazer a transferencia de mensagens 
por grupos de interesse e energia

Os arquivos usados para o treinamento dos algoritmos de cluster estao na pasta interestNodes os relatoios gerados estao na pasta reports
