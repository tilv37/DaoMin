import { TestBed } from '@angular/core/testing';

import { TagAdminServiceService } from './tag-admin-service.service';

describe('TagAdminServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TagAdminServiceService = TestBed.get(TagAdminServiceService);
    expect(service).toBeTruthy();
  });
});
