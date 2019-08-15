import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule) },
  { path: 'article', loadChildren: () => import('./pages/article/article.module').then(m => m.ArticleModule)},
  { path: 'category', loadChildren: () => import('./pages/category/category.module').then(m => m.CategoryModule)},
  { path: 'tag', loadChildren: () => import('./pages/tag/tag.module').then(m => m.TagModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
