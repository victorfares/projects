    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>

    typedef struct arv {
      int valor;
      struct arv *esq;
      struct arv *dir;
    } Arv;

    typedef struct lista {
      Arv* node;
      struct lista *prox;
    } Lista;

    typedef struct fila {
      Lista* ini;
      Lista* fim;
    } Fila;

    Arv* removeFila(Fila* pFila){
      if(pFila == NULL)
        return NULL;

      Lista* aux = pFila->ini;
      Arv* node;
      if(aux!=NULL){
        pFila->ini = pFila->ini->prox;
        node = aux->node;
        free(aux);
      }
      //Veirifa se ficou vazio
      if(pFila->ini == NULL)
        pFila->fim = NULL;
      return node;
    }

    void insereFila(Fila* pFila, Arv* info){
      Lista* novo = (Lista*) malloc(sizeof(Lista));
      novo->node = info;
      novo->prox = NULL;
      if(pFila->ini==NULL){
        pFila->ini = novo;
        pFila->fim = novo;
      }else{
        pFila->fim->prox = novo;
        pFila->fim = novo;
      }
    }

    Arv *insereArv(Arv *raiz, int info) {
      // Caso base
      if (raiz == NULL) {
        Arv *novo = (Arv *)malloc(sizeof(Arv));
        novo->valor = info;
        novo->esq = NULL;
        novo->dir = NULL;
        return novo;
      } else {
        if (raiz->valor > info) {
          raiz->esq = insereArv(raiz->esq, info);
        } else {
          raiz->dir = insereArv(raiz->dir, info);
        }
        return raiz;
      }
    }

    // Busca em Largura
    void BFS(Arv* raiz, FILE* f){
        Arv* node = NULL;
        Fila* F = (Fila*) malloc(sizeof(Fila));
        F->ini = NULL;
        F->fim = NULL;
        insereFila(F, raiz);

        while (F->ini != NULL) {
            node = removeFila(F);
            if(node != NULL){
              printf("%d, ", node->valor);
              fprintf(f, "%d\n",node->valor);
               if (node->esq!=NULL){
                  insereFila(F, node->esq);
               }else{
                   insereFila(F, NULL);
               }
               if (node->dir!=NULL){
                  insereFila(F, node->dir);
               }else{
                  insereFila(F, NULL);
               }
            }else{
              printf("%s, ", "None");
              fprintf(f, "%d\n",0);
            }
        }
    }

    // Funções adicionadas
    int contaNos(Arv* pRef) {
        int cont = 0;
        if(pRef != NULL) {
            cont += contaNos(pRef->esq);
            cont += contaNos(pRef->dir);
            cont++;
        }
        return cont;
    }

    int max_leaf_depth(Arv* raiz) {
        if(raiz == NULL) {
            return -1;
        } else {
            int esq = max_leaf_depth(raiz->esq);
            int dir = max_leaf_depth(raiz->dir);
            if (esq > dir) {
                return esq + 1;
            } else {
                return dir + 1;
            }
        }
    }

    int binary_leaf_count(Arv* raiz) {
        if(raiz == NULL) {
            return 0;
        }

        if(raiz->esq == NULL && raiz->dir == NULL) {
            return 1;
        } else {
            return binary_leaf_count(raiz->esq) +       binary_leaf_count(raiz->dir);
        }
    }

    int max_node_value(Arv* raiz) {
        int maiorvalor;

        if(raiz == NULL) {
            return 0;
        } else {
            maiorvalor = raiz->valor;
            int esq = max_node_value(raiz->esq);
            int dir = max_node_value(raiz->dir);

            if(esq > dir) {
                if (esq > maiorvalor) {
                    maiorvalor = esq;
                    return maiorvalor;
                } else {
                    return maiorvalor;
                }
            } else {
                if (dir > maiorvalor) {
                    maiorvalor = dir;
                    return maiorvalor;
                } else {
                    return maiorvalor;
                }
            }
        }
    }

int min_node_value (Arv* raiz) {
  int menorvalor;
  if (raiz == NULL) {
    return 0;
  } else {
    menorvalor = raiz->valor;
    int esq = min_node_value(raiz->esq);
    int dir = min_node_value(raiz->dir);

    if(esq<dir) {
      if (esq < menorvalor && esq != 0) {
        menorvalor = esq;
        return menorvalor;
      } else {
        return menorvalor;
      }
    } else {
      if(dir < menorvalor && dir != 0) {
        menorvalor = dir;
        return menorvalor;
      } else {
        return menorvalor;
      }
    }
  }
}

int min_leaf_depth(Arv *pRef, int nivel)
{
    if(pRef == NULL)
    {
        return INT_MAX;
    }
  
    if(pRef != NULL)
    {
        if(pRef->esq == NULL && pRef->dir == NULL){
            return nivel;
        }
        int esq = min_leaf_depth(pRef->esq, nivel + 1);
        int dir = min_leaf_depth(pRef->dir, nivel + 1);
      
        if(esq < dir)
        {
            return esq;
        }
          
        else{
            return dir;
        }
    }

    return nivel;
}

    int main() {
        FILE *fptr;
        fptr = fopen("bTree.txt", "w");

        if (fptr != NULL) {
            printf("Arquivo criado com sucesso!\n");
        } else {
            printf("Falha na criação do arquivo!!!\n");
            exit(0);
        }

        //Cadastrar a ABB neste parte do código
        Arv *raiz = NULL;

        // Protótipo:
        raiz = insereArv(raiz, 7);
        raiz = insereArv(raiz, 2);
        raiz = insereArv(raiz, 9);
        raiz = insereArv(raiz, 8);
        raiz = insereArv(raiz, 6);

        int numerodenosfolhas = binary_leaf_count(raiz);
        int leaf_depth = altura(raiz);
        int maiorvalor =  max_node_value(raiz);
        int menorvalor = min_node_value(raiz);

        /*
        * NÃO APAGAR!!
        * GERA O ARQUIVO btree.txt
        */
        BFS(raiz, fptr);

        printf("\n\n");
        printf("Total de Nos Folhas: %d \n", numerodenosfolhas);
        printf("Nível nó folha mais profundo: %d\n", leaf_depth);
        printf("Nível nó folha mais raso: XXXXX\n");
        printf("Maior nó: %d\n", maiorvalor);
        printf("Menor nó: %d\n", menorvalor);
        fclose(fptr);
        return 0;
    }
