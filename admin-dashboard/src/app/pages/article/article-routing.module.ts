import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { ArticleComponent } from "./article.component";
import { ListComponent } from './list/list.component';
import { EditComponent } from "./edit/edit.component";


const routes: Routes = [
    {
        path: '', component: ArticleComponent, children: [
            { path: 'list', component: ListComponent },
            { path: 'edit', component: EditComponent },
            { path: '', redirectTo: 'list', pathMatch: 'full' }
        ]
    }

    //{ path: 'path/:routeParam', component: MyComponent },
    //{ path: 'staticPath', component: ... },
    //{ path: '**', component: ... },
    //{ path: 'oldPath', redirectTo: '/staticPath' },
    //{ path: ..., component: ..., data: { message: 'Custom' }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ArticleRoutingModule { }
