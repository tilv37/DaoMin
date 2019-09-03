import { TestBed } from '@angular/core/testing';

import { CategoryAdminServiceService } from './category-admin-service.service';

describe('CategoryAdminServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CategoryAdminServiceService = TestBed.get(CategoryAdminServiceService);
    expect(service).toBeTruthy();
  });
});
